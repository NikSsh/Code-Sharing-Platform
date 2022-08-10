package code_sharing_platform.platform.controller;

import code_sharing_platform.platform.model.businessLayer.CodeSnippet;
import code_sharing_platform.platform.model.businessLayer.CodeSnippetService;
import code_sharing_platform.platform.model.businessLayer.CodeSnippetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class PlatformApiController {
    private final CodeSnippetService codeSnippetService;

    @Autowired
    public PlatformApiController(CodeSnippetServiceImpl codeSnippetService) {
        this.codeSnippetService = codeSnippetService;
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public ResponseEntity<List<CodeSnippet>> apiPOSTLatestCodeSnippets(HttpServletResponse response) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeSnippetService.listOf10RecentlyUploadedSnippets());
    }

    @GetMapping("/api/code/{UUID}")
    @ResponseBody
    public ResponseEntity<CodeSnippet> apiGetCodeByIdResponse(@PathVariable String UUID) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(codeSnippetService.getCodeSnippetById(UUID));
    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public ResponseEntity<Map<String, String>> apiPOST(@RequestBody CodeSnippet code) {
        String UUID = codeSnippetService.createCodeSnippet(code);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type",
                "application/json");
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(Map.of("id", UUID));
    }

}
