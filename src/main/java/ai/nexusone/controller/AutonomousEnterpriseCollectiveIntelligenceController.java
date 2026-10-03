package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.*;import jakarta.validation.Valid;import java.util.Map;
import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/autonomous-enterprise-collective-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseCollectiveIntelligenceController {
 private final AutonomousEnterpriseCollectiveIntelligenceService s; public AutonomousEnterpriseCollectiveIntelligenceController(AutonomousEnterpriseCollectiveIntelligenceService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseCollectiveIntelligenceResponse> generate(@Valid @RequestBody AutonomousEnterpriseCollectiveIntelligenceRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseCollectiveIntelligenceResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseCollectiveIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseCollectiveIntelligenceResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)AutonomousEnterpriseCollectiveIntelligencePriority priority,@RequestParam(required=false)AutonomousEnterpriseCollectiveIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseCollectiveIntelligenceResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseCollectiveIntelligenceActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseCollectiveIntelligenceResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseCollectiveIntelligenceActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseCollectiveIntelligenceResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseCollectiveIntelligenceActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseCollectiveIntelligenceResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseCollectiveIntelligenceActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseCollectiveIntelligenceAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_COLLECTIVE_INTELLIGENCE_FABRIC");}
}
