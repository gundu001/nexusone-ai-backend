package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.enums.*;
import ai.nexusone.service.AutonomousEnterpriseAgiCoordinationService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autonomous-enterprise-agi-coordination")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseAgiCoordinationController {
    private final AutonomousEnterpriseAgiCoordinationService service;
    public AutonomousEnterpriseAgiCoordinationController(AutonomousEnterpriseAgiCoordinationService service) { this.service = service; }

    @PostMapping("/generate")
    ResponseEntity<AutonomousEnterpriseAgiCoordinationResponse> generate(@Valid @RequestBody AutonomousEnterpriseAgiCoordinationRequest request) { return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request)); }
    @GetMapping("/{id}") AutonomousEnterpriseAgiCoordinationResponse get(@PathVariable Long id) { return service.get(id); }
    @GetMapping("/history") Page<AutonomousEnterpriseAgiCoordinationResponse> history(@PageableDefault(size=20, sort="createdAt", direction=Sort.Direction.DESC) Pageable p) { return service.history(p); }
    @GetMapping("/search") Page<AutonomousEnterpriseAgiCoordinationResponse> search(@RequestParam(required=false) String keyword, @RequestParam(required=false) AutonomousEnterpriseAgiCoordinationPriority priority, @RequestParam(required=false) AutonomousEnterpriseAgiCoordinationStatus status, @PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p) { return service.search(keyword,priority,status,p); }
    @PostMapping("/{id}/review") AutonomousEnterpriseAgiCoordinationResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseAgiCoordinationActionRequest r) { return service.review(id,r); }
    @PostMapping("/{id}/approve") AutonomousEnterpriseAgiCoordinationResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseAgiCoordinationActionRequest r) { return service.approve(id,r); }
    @PostMapping("/{id}/reject") AutonomousEnterpriseAgiCoordinationResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseAgiCoordinationActionRequest r) { return service.reject(id,r); }
    @PostMapping("/{id}/publish") AutonomousEnterpriseAgiCoordinationResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseAgiCoordinationActionRequest r) { return service.publish(id,r); }
    @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseAgiCoordinationAnalyticsResponse analytics() { return service.analytics(); }
    @GetMapping("/health") Map<String,String> health() { return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_AGI_COORDINATION_FABRIC"); }
}
