package ai.nexusone.controller;

import ai.nexusone.dto.request.CopilotQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.IncidentAnalysis;
import ai.nexusone.service.IncidentCopilotService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Page;



@RestController @RequestMapping("/api/incident-copilot") @CrossOrigin(origins="http://localhost:5173")
public class IncidentCopilotController {
 private final IncidentCopilotService service; public IncidentCopilotController(IncidentCopilotService service){this.service=service;}
 @GetMapping("/overview") public IncidentOverviewResponse overview(){return service.overview();}
 @GetMapping("/incidents") public List<IncidentResponse> incidents(){return service.incidents();}
 @GetMapping("/incidents/{incidentId}") public IncidentResponse incident(@PathVariable String incidentId){return service.incident(incidentId);}
 @GetMapping("/incidents/{incidentId}/rca") public RcaResponse rca(@PathVariable String incidentId){return service.rca(incidentId);}
 @GetMapping("/incidents/{incidentId}/remediation") public RemediationResponse remediation(@PathVariable String incidentId){return service.remediation(incidentId);}

 @PostMapping("/ask")
 public CopilotAnswerResponse ask(
         @Valid @RequestBody CopilotQueryRequest request) {

  System.out.println("=== ASK API HIT ===");
  System.out.println("IncidentId = " + request.incidentId());
  System.out.println("Question   = " + request.question());

  return service.ask(request);
 }

 @GetMapping("/history")
 public Page<IncidentAnalysis> history(
         @RequestParam(defaultValue = "0")
         int page,

         @RequestParam(defaultValue = "20")
         int size){

  return service.history(page,size);
 }

 @GetMapping("/history/{incidentId}")
 public Page<IncidentAnalysis> historyByIncident(

         @PathVariable String incidentId,

         @RequestParam(defaultValue = "0")
         int page,

         @RequestParam(defaultValue = "20")
         int size){

  return service.historyByIncident(
          incidentId,
          page,
          size);
 }
}
