package ai.nexusone.controller;
import ai.nexusone.dto.*;
import ai.nexusone.enums.*;
import ai.nexusone.service.AutonomousEnterpriseMAIntelligenceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/autonomous-enterprise-ma-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseMAIntelligenceController {
    private final AutonomousEnterpriseMAIntelligenceService service;
    public AutonomousEnterpriseMAIntelligenceController(AutonomousEnterpriseMAIntelligenceService service){this.service=service;}
    @PostMapping("/generate") public ResponseEntity<AutonomousEnterpriseMAIntelligenceResponse> generate(@Valid @RequestBody AutonomousEnterpriseMAIntelligenceRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));}
    @GetMapping("/{id}") public AutonomousEnterpriseMAIntelligenceResponse get(@PathVariable Long id){return service.getById(id);}
    @GetMapping("/history") public Page<AutonomousEnterpriseMAIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable pageable){return service.getAll(pageable);}
    @GetMapping("/search") public Page<AutonomousEnterpriseMAIntelligenceResponse> search(@RequestParam(required=false) String keyword,@RequestParam(required=false) AutonomousEnterpriseMAIntelligencePriority priority,@RequestParam(required=false) AutonomousEnterpriseMAIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable pageable){return service.search(keyword,priority,status,pageable);}
}
