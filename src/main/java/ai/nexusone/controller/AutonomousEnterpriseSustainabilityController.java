package ai.nexusone.controller;
import ai.nexusone.dto.*; import ai.nexusone.enums.*; import ai.nexusone.service.*; import jakarta.validation.Valid;
import org.springframework.data.domain.*; import org.springframework.data.web.PageableDefault; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.Map;
@RestController @RequestMapping("/api/autonomous-enterprise-sustainability") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseSustainabilityController {
 private final AutonomousEnterpriseSustainabilityService s;
 public AutonomousEnterpriseSustainabilityController(AutonomousEnterpriseSustainabilityService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseSustainabilityResponse> generate(@Valid @RequestBody AutonomousEnterpriseSustainabilityRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseSustainabilityResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseSustainabilityResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseSustainabilityResponse> search(@RequestParam(required=false) String keyword,@RequestParam(required=false) AutonomousEnterpriseSustainabilityPriority priority,@RequestParam(required=false) AutonomousEnterpriseSustainabilityStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseSustainabilityResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseSustainabilityActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseSustainabilityResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseSustainabilityActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseSustainabilityResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseSustainabilityActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseSustainabilityResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseSustainabilityActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseSustainabilityAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_SUSTAINABILITY_INTELLIGENCE_FABRIC");}
}
