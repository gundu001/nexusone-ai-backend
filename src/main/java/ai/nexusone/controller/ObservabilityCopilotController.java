package ai.nexusone.controller;
import ai.nexusone.dto.request.ObservabilityQueryRequest; import ai.nexusone.dto.response.*; import ai.nexusone.entity.ObservabilityAnalysis; import ai.nexusone.service.ObservabilityCopilotService; import jakarta.validation.Valid; import org.springframework.data.domain.Page; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/observability-copilot") public class ObservabilityCopilotController{
 private final ObservabilityCopilotService service; public ObservabilityCopilotController(ObservabilityCopilotService s){service=s;}
 @GetMapping("/overview") public ObservabilityOverviewResponse overview(){return service.overview();}
 @GetMapping("/services") public List<ServiceHealthResponse> services(){return service.services();}
 @GetMapping("/services/{name}/insight") public ObservabilityInsightResponse insight(@PathVariable String name){return service.insight(name);}
 @PostMapping("/analyze") public ObservabilityInsightResponse analyze(@Valid @RequestBody ObservabilityQueryRequest q){return service.analyze(q);}
 @GetMapping("/history") public Page<ObservabilityAnalysis> history(@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="20")int size){return service.history(page,size);}
 @ExceptionHandler(NoSuchElementException.class) public ResponseEntity<Map<String,String>> missing(NoSuchElementException e){return ResponseEntity.status(404).body(Map.of("message",e.getMessage()));}
}
