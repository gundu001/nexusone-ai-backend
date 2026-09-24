package ai.nexusone.controller;
import ai.nexusone.dto.*; import ai.nexusone.enums.*; import ai.nexusone.service.ChairmanIntelligenceService; import jakarta.validation.Valid; import java.util.Map;
import org.springframework.data.domain.*; import org.springframework.data.web.PageableDefault; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/chairman-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class ChairmanIntelligenceController { private final ChairmanIntelligenceService service; public ChairmanIntelligenceController(ChairmanIntelligenceService s){service=s;}
 @PostMapping("/generate") ResponseEntity<ChairmanIntelligenceResponse> generate(@Valid @RequestBody ChairmanIntelligenceRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(r));}
 @GetMapping("/{id}") ChairmanIntelligenceResponse get(@PathVariable Long id){return service.get(id);}
 @GetMapping("/history") Page<ChairmanIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return service.history(p);}
 @GetMapping("/search") Page<ChairmanIntelligenceResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)ChairmanPriority priority,@RequestParam(required=false)ChairmanIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return service.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") ChairmanIntelligenceResponse review(@PathVariable Long id,@Valid @RequestBody ChairmanActionRequest r){return service.review(id,r);}
 @PostMapping("/{id}/approve") ChairmanIntelligenceResponse approve(@PathVariable Long id,@Valid @RequestBody ChairmanActionRequest r){return service.approve(id,r);}
 @PostMapping("/{id}/reject") ChairmanIntelligenceResponse reject(@PathVariable Long id,@Valid @RequestBody ChairmanActionRequest r){return service.reject(id,r);}
 @PostMapping("/{id}/execute") ChairmanIntelligenceResponse execute(@PathVariable Long id,@Valid @RequestBody ChairmanActionRequest r){return service.execute(id,r);}
 @GetMapping({"/analytics","/dashboard"}) ChairmanAnalyticsResponse analytics(){return service.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_CHAIRMAN_INTELLIGENCE");}
}
