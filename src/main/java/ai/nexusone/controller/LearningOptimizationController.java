package ai.nexusone.controller;
import ai.nexusone.dto.*; import ai.nexusone.service.LearningOptimizationService; import jakarta.validation.Valid; import org.springframework.data.domain.*; import org.springframework.data.web.PageableDefault; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/learning-optimization") @CrossOrigin(origins="http://localhost:5173")
public class LearningOptimizationController {
 private final LearningOptimizationService service; public LearningOptimizationController(LearningOptimizationService service){this.service=service;}
 @PostMapping("/learn") public ResponseEntity<LearningResponse> learn(@Valid @RequestBody LearningRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.learn(r));}
 @GetMapping("/{id}") public LearningResponse get(@PathVariable Long id){return service.get(id);} @GetMapping("/outcome/{outcomeId}") public List<LearningResponse> byOutcome(@PathVariable Long outcomeId){return service.byOutcome(outcomeId);}
 @GetMapping("/history") public Page<LearningResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return service.history(p);} @GetMapping("/analytics") public LearningAnalyticsResponse analytics(){return service.analytics();}
 @PostMapping("/{id}/apply") public LearningResponse apply(@PathVariable Long id,@Valid @RequestBody ApplyLearningRequest r){return service.apply(id,r);} @PostMapping("/{id}/reject") public LearningResponse reject(@PathVariable Long id){return service.reject(id);}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","mode","DETERMINISTIC_CONTINUOUS_LEARNING");}
}
