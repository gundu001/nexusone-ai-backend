package ai.nexusone.controller;
import ai.nexusone.dto.*;
import ai.nexusone.enums.*;
import ai.nexusone.service.BoardroomIntelligenceService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/boardroom-intelligence") @CrossOrigin(origins="http://localhost:5173")
public class BoardroomIntelligenceController {
 private final BoardroomIntelligenceService service; public BoardroomIntelligenceController(BoardroomIntelligenceService s){service=s;}
 @PostMapping("/generate") ResponseEntity<BoardroomBriefingResponse> generate(@Valid @RequestBody BoardroomBriefingRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(r));}
 @GetMapping("/{id}") BoardroomBriefingResponse get(@PathVariable Long id){return service.get(id);}
 @GetMapping("/history") Page<BoardroomBriefingResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return service.history(p);}
 @GetMapping("/search") Page<BoardroomBriefingResponse> search(@RequestParam(required=false)String keyword,@RequestParam(required=false)BoardroomPriority priority,@RequestParam(required=false)BoardroomBriefingStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC)Pageable p){return service.search(keyword,priority,status,p);}
 @PostMapping("/{id}/review") BoardroomBriefingResponse review(@PathVariable Long id,@Valid @RequestBody BoardroomActionRequest r){return service.review(id,r);}
 @PostMapping("/{id}/approve") BoardroomBriefingResponse approve(@PathVariable Long id,@Valid @RequestBody BoardroomActionRequest r){return service.approve(id,r);}
 @PostMapping("/{id}/reject") BoardroomBriefingResponse reject(@PathVariable Long id,@Valid @RequestBody BoardroomActionRequest r){return service.reject(id,r);}
 @PostMapping("/{id}/execute") BoardroomBriefingResponse execute(@PathVariable Long id,@Valid @RequestBody BoardroomActionRequest r){return service.execute(id,r);}
 @GetMapping({"/analytics","/dashboard"}) BoardroomAnalyticsResponse analytics(){return service.analytics();}
 @GetMapping("/health") Map<String,String> health(){return Map.of("status","UP","mode","AUTONOMOUS_ENTERPRISE_BOARDROOM_INTELLIGENCE");}
}
