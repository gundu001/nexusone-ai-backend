package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.*;import jakarta.validation.Valid;
import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestController @RequestMapping("/api/autonomous-enterprise-resilience") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseResilienceController {
 private final AutonomousEnterpriseResilienceService s;
 public AutonomousEnterpriseResilienceController(AutonomousEnterpriseResilienceService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseResilienceResponse> generate(@Valid @RequestBody AutonomousEnterpriseResilienceRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseResilienceResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseResilienceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseResilienceResponse> search(@RequestParam(required=false) String keyword,@RequestParam(required=false) AutonomousEnterpriseResiliencePriority priority,@RequestParam(required=false) AutonomousEnterpriseResilienceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseResilienceResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseResilienceActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseResilienceResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseResilienceActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseResilienceResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseResilienceActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseResilienceResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseResilienceActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseResilienceAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_RESILIENCE_INTELLIGENCE_FABRIC");}
}
