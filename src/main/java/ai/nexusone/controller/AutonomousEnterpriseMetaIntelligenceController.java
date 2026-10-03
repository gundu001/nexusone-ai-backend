package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.*;import jakarta.validation.Valid;import java.util.Map;
import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/autonomous-enterprise-meta-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseMetaIntelligenceController {
 private final AutonomousEnterpriseMetaIntelligenceService s;
 public AutonomousEnterpriseMetaIntelligenceController(AutonomousEnterpriseMetaIntelligenceService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseMetaIntelligenceResponse> generate(@Valid @RequestBody AutonomousEnterpriseMetaIntelligenceRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseMetaIntelligenceResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseMetaIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseMetaIntelligenceResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)AutonomousEnterpriseMetaIntelligencePriority priority,@RequestParam(required=false)AutonomousEnterpriseMetaIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseMetaIntelligenceResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseMetaIntelligenceActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseMetaIntelligenceResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseMetaIntelligenceActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseMetaIntelligenceResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseMetaIntelligenceActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseMetaIntelligenceResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseMetaIntelligenceActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseMetaIntelligenceAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_META_INTELLIGENCE_FABRIC");}
}
