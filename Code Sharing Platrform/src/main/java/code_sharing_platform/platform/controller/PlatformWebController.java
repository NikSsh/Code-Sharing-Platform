package code_sharing_platform.platform.controller;

import code_sharing_platform.platform.model.businessLayer.CodeSnippet;
import code_sharing_platform.platform.model.businessLayer.CodeSnippetService;
import code_sharing_platform.platform.model.businessLayer.CodeSnippetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlatformWebController {
    private final CodeSnippetService codeSnippetService;

    @Autowired
    public PlatformWebController(CodeSnippetServiceImpl codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/code")
    public String webResponse(Model model, @RequestParam String UUID) {
        CodeSnippet currentCodeSnippet = codeSnippetService.getCodeSnippetById(UUID);
        model.addAttribute("codeSnippet", currentCodeSnippet);

        return "concreteSnippet";
    }

    @GetMapping("/")
    public String webIndexCodePageResponse() {
        return "index";
    }

    @GetMapping("/code/new")
    public String webNewCodePageResponse() {
        return "createSnippet";
    }

    @GetMapping("/code/latest")
    public String webLatestSnippetsResponse(Model model) {
        List<CodeSnippet> snippetsList = codeSnippetService.listOf10RecentlyUploadedSnippets();
        model.addAttribute("codeSnippets", snippetsList);
        return "latestSnippets";
    }
}
