package ai.nexusone.controller;

import ai.nexusone.dto.devopscommand.*;
import ai.nexusone.service.DevOpsCommandCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devops-command-center")
@CrossOrigin(origins = "*")
public class DevOpsCommandCenterController {
    private final DevOpsCommandCenterService service;

    public DevOpsCommandCenterController(DevOpsCommandCenterService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<CommandCenterOverviewResponse> getOverview() {
        return ResponseEntity.ok(service.getOverview());
    }

    @GetMapping("/missions")
    public ResponseEntity<List<MissionResponse>> getMissions() {
        return ResponseEntity.ok(service.getMissions());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<DevOpsRecommendationResponse>> getRecommendations() {
        return ResponseEntity.ok(service.getRecommendations());
    }

    @GetMapping("/timeline")
    public ResponseEntity<List<OperationTimelineResponse>> getTimeline() {
        return ResponseEntity.ok(service.getTimeline());
    }

    @PostMapping("/execute")
    public ResponseEntity<CommandExecutionResponse> execute(@RequestBody CommandExecutionRequest request) {
        return ResponseEntity.ok(service.execute(request));
    }

    @GetMapping("/commands")
    public ResponseEntity<List<String>> getSupportedCommands() {
        return ResponseEntity.ok(service.getSupportedCommands());
    }
}
