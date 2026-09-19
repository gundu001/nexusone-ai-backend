package ai.nexusone.controller;
import ai.nexusone.dto.request.*; import ai.nexusone.dto.response.*; import ai.nexusone.service.CrossCloudFailoverService;
import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/failover")
public class CrossCloudFailoverController {
 private final CrossCloudFailoverService service; public CrossCloudFailoverController(CrossCloudFailoverService s){service=s;}
 @GetMapping("/overview") public FailoverOverviewResponse overview(){return service.overview();}
 @GetMapping("/plans") public List<ResiliencePlanResponse> plans(){return service.getPlans();}
 @GetMapping("/executions") public List<FailoverExecutionResponse> executions(){return service.getExecutions();}
 @PostMapping("/plans") public ResponseEntity<ResiliencePlanResponse> create(@Valid @RequestBody ResiliencePlanRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.createPlan(r));}
 @PostMapping("/simulate") public FailoverExecutionResponse simulate(@Valid @RequestBody FailoverActionRequest r){return service.simulate(r);}
 @PostMapping("/execute") public FailoverExecutionResponse execute(@Valid @RequestBody FailoverActionRequest r){return service.execute(r);}
 @PostMapping("/rollback") public FailoverExecutionResponse rollback(@Valid @RequestBody FailoverActionRequest r){return service.rollback(r);}
}
