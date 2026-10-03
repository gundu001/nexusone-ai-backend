package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.*;import jakarta.validation.Valid;import java.util.Map;
import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/autonomous-enterprise-omni-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseOmniIntelligenceController {
 private final AutonomousEnterpriseOmniIntelligenceService s; public AutonomousEnterpriseOmniIntelligenceController(AutonomousEnterpriseOmniIntelligenceService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseOmniIntelligenceResponse> generate(@Valid @RequestBody AutonomousEnterpriseOmniIntelligenceRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseOmniIntelligenceResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseOmniIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseOmniIntelligenceResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)AutonomousEnterpriseOmniIntelligencePriority priority,@RequestParam(required=false)AutonomousEnterpriseOmniIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseOmniIntelligenceResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseOmniIntelligenceActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseOmniIntelligenceResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseOmniIntelligenceActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseOmniIntelligenceResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseOmniIntelligenceActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseOmniIntelligenceResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseOmniIntelligenceActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseOmniIntelligenceAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_OMNI_INTELLIGENCE_FABRIC");}
}
