package ai.nexusone.controller;
import ai.nexusone.dto.*;import ai.nexusone.enums.*;import ai.nexusone.service.MarketIntelligenceService;import jakarta.validation.Valid;import java.util.Map;import org.springframework.data.domain.*;import org.springframework.data.web.PageableDefault;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/market-competitive-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class MarketIntelligenceController{
 private final MarketIntelligenceService service;public MarketIntelligenceController(MarketIntelligenceService s){service=s;}
 @PostMapping("/generate") ResponseEntity<MarketIntelligenceResponse> generate(@Valid @RequestBody MarketIntelligenceRequest q){return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(q));}
 @GetMapping("/{id}") MarketIntelligenceResponse get(@PathVariable Long id){return service.get(id);}
 @GetMapping("/history") Page<MarketIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return service.history(p);}
 @GetMapping("/search") Page<MarketIntelligenceResponse> search(@RequestParam(required=false) String keyword,@RequestParam(required=false) MarketPriority priority,@RequestParam(required=false) MarketIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return service.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") MarketIntelligenceResponse review(@PathVariable Long id,@Valid @RequestBody MarketActionRequest q){return service.review(id,q);}
 @PostMapping("/{id}/approve") MarketIntelligenceResponse approve(@PathVariable Long id,@Valid @RequestBody MarketActionRequest q){return service.approve(id,q);}
 @PostMapping("/{id}/reject") MarketIntelligenceResponse reject(@PathVariable Long id,@Valid @RequestBody MarketActionRequest q){return service.reject(id,q);}
 @PostMapping("/{id}/publish") MarketIntelligenceResponse publish(@PathVariable Long id,@Valid @RequestBody MarketActionRequest q){return service.publish(id,q);}
 @GetMapping({"/analytics","/dashboard"}) MarketAnalyticsResponse analytics(){return service.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_MARKET_COMPETITIVE_INTELLIGENCE");}
}
