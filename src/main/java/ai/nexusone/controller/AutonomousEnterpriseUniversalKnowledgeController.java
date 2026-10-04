package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.*;import jakarta.validation.Valid;import java.util.Map;
import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/autonomous-enterprise-universal-knowledge") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseUniversalKnowledgeController {
 private final AutonomousEnterpriseUniversalKnowledgeService s; public AutonomousEnterpriseUniversalKnowledgeController(AutonomousEnterpriseUniversalKnowledgeService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseUniversalKnowledgeResponse> generate(@Valid @RequestBody AutonomousEnterpriseUniversalKnowledgeRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseUniversalKnowledgeResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseUniversalKnowledgeResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseUniversalKnowledgeResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)AutonomousEnterpriseUniversalKnowledgePriority priority,@RequestParam(required=false)AutonomousEnterpriseUniversalKnowledgeStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseUniversalKnowledgeResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseUniversalKnowledgeActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseUniversalKnowledgeResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseUniversalKnowledgeActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseUniversalKnowledgeResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseUniversalKnowledgeActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseUniversalKnowledgeResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseUniversalKnowledgeActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseUniversalKnowledgeAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_UNIVERSAL_KNOWLEDGE_FABRIC");}
}
