package code_sharing_platform.platform.model.businessLayer;

import java.util.List;

public interface CodeSnippetService {
    String createCodeSnippet(CodeSnippet codeSnippet);
    void updateCodeSnippet(CodeSnippet codeSnippet);
    CodeSnippet getCodeSnippetById(String UUID);
    List<CodeSnippet> listOf10RecentlyUploadedSnippets();
}
