package ai.nexusone.controller;

import ai.nexusone.dto.AutonomousActionResponse;
import ai.nexusone.dto.CommandCenterOverviewResponse;
import ai.nexusone.dto.OperationalAlertResponse;
import ai.nexusone.dto.RecommendationResponse;
import ai.nexusone.dto.ReleaseHealthResponse;
import ai.nexusone.dto.ReleasePriorityResponse;
import ai.nexusone.service.DeliveryCommandCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/command-center")
public class DeliveryCommandCenterController {
    private final DeliveryCommandCenterService service;
    public DeliveryCommandCenterController(DeliveryCommandCenterService service){this.service=service;}
    @GetMapping("/overview") public ResponseEntity<CommandCenterOverviewResponse> overview(){return ResponseEntity.ok(service.getOverview());}
    @GetMapping("/release-health") public ResponseEntity<List<ReleaseHealthResponse>> health(){return ResponseEntity.ok(service.getReleaseHealth());}
    @GetMapping("/autonomous-actions") public ResponseEntity<List<AutonomousActionResponse>> actions(){return ResponseEntity.ok(service.getAutonomousActions());}
    @GetMapping("/recommendations") public ResponseEntity<List<RecommendationResponse>> recommendations(){return ResponseEntity.ok(service.getRecommendations());}
    @GetMapping("/release-priorities") public ResponseEntity<List<ReleasePriorityResponse>> priorities(){return ResponseEntity.ok(service.getReleasePriorities());}
    @GetMapping("/operational-alerts") public ResponseEntity<List<OperationalAlertResponse>> alerts(){return ResponseEntity.ok(service.getOperationalAlerts());}
}
