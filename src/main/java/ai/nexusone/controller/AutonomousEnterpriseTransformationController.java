package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.*;import jakarta.validation.Valid;import java.util.Map;
import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/autonomous-enterprise-transformation") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseTransformationController {
 private final AutonomousEnterpriseTransformationService s; public AutonomousEnterpriseTransformationController(AutonomousEnterpriseTransformationService s){this.s=s;}
 @PostMapping("/generate") ResponseEntity<AutonomousEnterpriseTransformationResponse> generate(@Valid @RequestBody AutonomousEnterpriseTransformationRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(s.generate(q));}
 @GetMapping("/{id}") AutonomousEnterpriseTransformationResponse get(@PathVariable Long id){return s.get(id);}
 @GetMapping("/history") Page<AutonomousEnterpriseTransformationResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return s.history(p);}
 @GetMapping("/search") Page<AutonomousEnterpriseTransformationResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)AutonomousEnterpriseTransformationPriority priority,@RequestParam(required=false)AutonomousEnterpriseTransformationStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return s.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") AutonomousEnterpriseTransformationResponse review(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseTransformationActionRequest q){return s.review(id,q);}
 @PostMapping("/{id}/approve") AutonomousEnterpriseTransformationResponse approve(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseTransformationActionRequest q){return s.approve(id,q);}
 @PostMapping("/{id}/reject") AutonomousEnterpriseTransformationResponse reject(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseTransformationActionRequest q){return s.reject(id,q);}
 @PostMapping("/{id}/publish") AutonomousEnterpriseTransformationResponse publish(@PathVariable Long id,@Valid @RequestBody AutonomousEnterpriseTransformationActionRequest q){return s.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) AutonomousEnterpriseTransformationAnalyticsResponse analytics(){return s.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_TRANSFORMATION_FABRIC");}
}
