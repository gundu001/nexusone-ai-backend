package ai.nexusone.controller;

import ai.nexusone.dto.request.*;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.SelfHealingExecution;
import ai.nexusone.service.SelfHealingCopilotService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/self-healing-copilot")
public class SelfHealingCopilotController {
    private final SelfHealingCopilotService service;
    public SelfHealingCopilotController(SelfHealingCopilotService service){this.service=service;}
    @GetMapping("/overview") public SelfHealingOverviewResponse overview(){return service.overview();}
    @GetMapping("/candidates") public List<SelfHealingCandidateResponse> candidates(){return service.candidates();}
    @GetMapping("/applications/{application}/plan") public SelfHealingPlanResponse plan(@PathVariable String application){return service.plan(application);}
    @PostMapping("/ask") public SelfHealingPlanResponse ask(@Valid @RequestBody SelfHealingQueryRequest request){return service.ask(request);}
    @PostMapping("/execute") public SelfHealingExecutionResponse execute(@Valid @RequestBody SelfHealingExecuteRequest request){return service.execute(request);}
    @GetMapping("/history") public Page<SelfHealingExecution> history(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size){return service.history(page,size);}
    @GetMapping("/applications/{application}/history") public Page<SelfHealingExecution> historyByApplication(@PathVariable String application,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size){return service.historyByApplication(application,page,size);}
    @ExceptionHandler(NoSuchElementException.class) public ResponseEntity<Map<String,String>> missing(NoSuchElementException e){return ResponseEntity.status(404).body(Map.of("message",e.getMessage()));}
    @ExceptionHandler(IllegalArgumentException.class) public ResponseEntity<Map<String,String>> invalid(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}
}
