package code_sharing_platform.platform.model.databaseLayer;

import code_sharing_platform.platform.model.businessLayer.CodeSnippet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CodeSnippetRepository extends CrudRepository<CodeSnippet, String> {
}
