package ai.nexusone.controller;

import ai.nexusone.dto.response.eoc.*;
import ai.nexusone.service.EnterpriseOperationsCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eoc")
@CrossOrigin(origins = "*")
public class EnterpriseOperationsCenterController {
    private final EnterpriseOperationsCenterService service;

    public EnterpriseOperationsCenterController(EnterpriseOperationsCenterService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public ResponseEntity<EocOverviewResponse> overview() {
        return ResponseEntity.ok(service.getOverview());
    }

    @GetMapping("/health")
    public ResponseEntity<EocHealthResponse> health() {
        return ResponseEntity.ok(service.getHealth());
    }

    @GetMapping("/incidents")
    public ResponseEntity<List<EocIncidentResponse>> incidents() {
        return ResponseEntity.ok(service.getIncidents());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<List<EocRecommendationResponse>> recommendations() {
        return ResponseEntity.ok(service.getRecommendations());
    }

    @GetMapping("/operations")
    public ResponseEntity<EocOperationsResponse> operations() {
        return ResponseEntity.ok(service.getOperations());
    }
}
