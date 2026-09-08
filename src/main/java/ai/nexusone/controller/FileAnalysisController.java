package ai.nexusone.controller;

import ai.nexusone.dto.FileAnalysisResult;
import ai.nexusone.service.FileAnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/repositories")
@CrossOrigin(origins = "http://localhost:5173")
public class FileAnalysisController {
    private final FileAnalysisService fileAnalysisService;

    public FileAnalysisController(FileAnalysisService fileAnalysisService) {
        this.fileAnalysisService = fileAnalysisService;
    }

    @GetMapping("/file-analysis")
    public ResponseEntity<?> analyze(@RequestParam("repoName") String repoName) {
        try {
            FileAnalysisResult result = fileAnalysisService.analyzeRepository(repoName);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("status", "FAILED", "message", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "FAILED", "message", "Unable to analyze files: " + e.getMessage()));
        }
    }
}
