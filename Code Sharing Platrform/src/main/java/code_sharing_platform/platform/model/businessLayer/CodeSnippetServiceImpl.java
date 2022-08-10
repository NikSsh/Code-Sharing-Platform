package code_sharing_platform.platform.model.businessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import code_sharing_platform.platform.model.databaseLayer.CodeSnippetRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * Provides interaction with DB via codeSnippetRepository
 */
@Service
public class CodeSnippetServiceImpl implements CodeSnippetService{
    private final CodeSnippetRepository codeSnippetRepository;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();

    @Autowired
    public CodeSnippetServiceImpl(CodeSnippetRepository codeSnippetRepository) {
        this.codeSnippetRepository = codeSnippetRepository;
    }

    /**
     * @return list of CodeSnippet objects that contains last 10 unrestricted code snippets
     * @throws ResponseStatusException HttpStatus.NOT_FOUND if DB doesn't contain any unrestricted code snippets
     */
    public List<CodeSnippet> listOf10RecentlyUploadedSnippets() {
        Iterable<CodeSnippet> codeSnippets;
        synchronized (readLock) {
            codeSnippets = codeSnippetRepository.findAll();
        }
        List<CodeSnippet> codeSnippetList = new ArrayList<>();
        codeSnippets.forEach(codeSnippet -> {
            if (codeSnippet.getRestriction() == CodeSnippet.RestrictionType.UNRESTRICTED) {
                codeSnippetList.add(codeSnippet);
            }
        });

        if (codeSnippetList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Collections.reverse(codeSnippetList);
        return codeSnippetList.stream()
                .limit(10)
                .collect(Collectors.toList());
    }

    /**
     * @param codeSnippet object of the CodeSnippet class that is updated and saved in DB
     * @return id of the saved codeSnippet in DB
     */
    public String createCodeSnippet(CodeSnippet codeSnippet) {
        String uuid = UUID.randomUUID().toString();
        codeSnippet.setUUID(uuid);
        codeSnippet.setLastUpdateTime(LocalDateTime.now());
        codeSnippet.setUpRestrictions();

        synchronized (writeLock) {
            codeSnippetRepository.save(codeSnippet);
        }
        return uuid;
    }

    /**
     * @param codeSnippet object of the CodeSnippet class that is updated and saved in DB
     */
    public void updateCodeSnippet(CodeSnippet codeSnippet) {
        codeSnippet.setLastUpdateTime(LocalDateTime.now());
        synchronized (writeLock) {
            codeSnippetRepository.save(codeSnippet);
        }
    }

    /**
     * @param UUID String that represents id of the snippet that we want to retrieve from DB
     * @return snippet that we retrieved from DB
     * @throws ResponseStatusException HttpStatus.NOT_FOUND if snippet was not found or at least one of limits is reached
     */
    public CodeSnippet getCodeSnippetById(String UUID) {
        CodeSnippet codeSnippet;
        synchronized (readLock) {
            codeSnippet = codeSnippetRepository.findById(UUID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        boolean toBeDeleted = codeSnippet.updateRestrictions();

        synchronized (writeLock) {
            codeSnippetRepository.save(codeSnippet);
            if (toBeDeleted) {
                codeSnippetRepository.delete(codeSnippet);
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }

        return codeSnippet;
    }


}
