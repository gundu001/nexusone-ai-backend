package ai.nexusone.controller;

import ai.nexusone.dto.RepositoryScanResult;
import ai.nexusone.service.RepositoryScannerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/repositories")
@CrossOrigin(origins = "http://localhost:5173")
public class RepositoryScanController {

    private final RepositoryScannerService scannerService;

    public RepositoryScanController(
            RepositoryScannerService scannerService
    ) {
        this.scannerService = scannerService;
    }

    @GetMapping("/scan")
    public ResponseEntity<?> scanRepository(
            @RequestParam("repoName") String repoName
    ) {
        try {
            RepositoryScanResult result =
                    scannerService.scanRepository(repoName);

            return ResponseEntity.ok(result);

        } catch (IllegalArgumentException exception) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of(
                            "status", "FAILED",
                            "message", exception.getMessage()
                    ));

        } catch (IOException exception) {
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of(
                            "status", "FAILED",
                            "message",
                            "Unable to scan repository: "
                                    + exception.getMessage()
                    ));
        }
    }
}