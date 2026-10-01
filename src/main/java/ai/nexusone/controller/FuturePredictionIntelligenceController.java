package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.FuturePredictionIntelligenceService;import jakarta.validation.Valid;import java.util.Map;import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/future-prediction-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class FuturePredictionIntelligenceController{private final FuturePredictionIntelligenceService service;public FuturePredictionIntelligenceController(FuturePredictionIntelligenceService s){service=s;}
@PostMapping("/generate") ResponseEntity<FuturePredictionIntelligenceResponse> generate(@Valid @RequestBody FuturePredictionIntelligenceRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(q));}
@GetMapping("/{id}") FuturePredictionIntelligenceResponse get(@PathVariable Long id){return service.get(id);}
@GetMapping("/history") Page<FuturePredictionIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return service.history(p);}
@GetMapping("/search") Page<FuturePredictionIntelligenceResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)FuturePredictionPriority priority,@RequestParam(required=false)FuturePredictionStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return service.search(keyword,priority,status,p);}
@PostMapping("/{id}/review") FuturePredictionIntelligenceResponse review(@PathVariable Long id,@Valid @RequestBody FuturePredictionActionRequest q){return service.review(id,q);}
@PostMapping("/{id}/approve") FuturePredictionIntelligenceResponse approve(@PathVariable Long id,@Valid @RequestBody FuturePredictionActionRequest q){return service.approve(id,q);}
@PostMapping("/{id}/reject") FuturePredictionIntelligenceResponse reject(@PathVariable Long id,@Valid @RequestBody FuturePredictionActionRequest q){return service.reject(id,q);}
@PostMapping("/{id}/publish") FuturePredictionIntelligenceResponse publish(@PathVariable Long id,@Valid @RequestBody FuturePredictionActionRequest q){return service.publish(id,q);}
@GetMapping({"/analytics","/dashboard"}) FuturePredictionAnalyticsResponse analytics(){return service.analytics();}
@GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_FUTURE_PREDICTION_INTELLIGENCE");}}
