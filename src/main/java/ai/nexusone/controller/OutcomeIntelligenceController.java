package ai.nexusone.controller;

import ai.nexusone.dto.*;
import ai.nexusone.service.OutcomeIntelligenceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/outcome-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class OutcomeIntelligenceController {
 private final OutcomeIntelligenceService service;
 public OutcomeIntelligenceController(OutcomeIntelligenceService service){this.service=service;}
 @PostMapping("/record") public ResponseEntity<OutcomeResponse> record(@Valid @RequestBody OutcomeRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.recordOutcome(r));}
 @GetMapping("/{id}") public OutcomeResponse get(@PathVariable Long id){return service.get(id);}
 @GetMapping("/execution/{executionId}") public List<OutcomeResponse> byExecution(@PathVariable Long executionId){return service.byExecution(executionId);}
 @GetMapping("/history") public Page<OutcomeResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p){return service.history(p);}
 @GetMapping("/analytics") public OutcomeAnalyticsResponse analytics(){return service.analytics();}
 @PostMapping("/{id}/reassess") public OutcomeResponse reassess(@PathVariable Long id){return service.reassess(id);}
 @GetMapping("/health") public Map<String,String> health(){return Map.of("status","UP","mode","DETERMINISTIC_OUTCOME_SCORING");}
}
