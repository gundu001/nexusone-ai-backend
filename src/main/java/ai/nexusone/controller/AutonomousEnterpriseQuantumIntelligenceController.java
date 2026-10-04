package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.*;import jakarta.validation.Valid;import java.util.Map;
import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/autonomous-enterprise-quantum-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseQuantumIntelligenceController {
 private final AutonomousEnterpriseQuantumIntelligenceService s; public AutonomousEnterpriseQuantumIntelligenceController(AutonomousEnterpriseQuantumIntelligenceService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseQuantumIntelligenceResponse> generate(@Valid @RequestBody AutonomousEnterpriseQuantumIntelligenceRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseQuantumIntelligenceResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseQuantumIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseQuantumIntelligenceResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)AutonomousEnterpriseQuantumIntelligencePriority priority,@RequestParam(required=false)AutonomousEnterpriseQuantumIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseQuantumIntelligenceResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseQuantumIntelligenceActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseQuantumIntelligenceResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseQuantumIntelligenceActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseQuantumIntelligenceResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseQuantumIntelligenceActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseQuantumIntelligenceResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseQuantumIntelligenceActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseQuantumIntelligenceAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_QUANTUM_INTELLIGENCE_FABRIC");}
}
