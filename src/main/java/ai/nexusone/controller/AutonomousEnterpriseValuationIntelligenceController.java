package ai.nexusone.controller;
import ai.nexusone.dto.*;
import ai.nexusone.enums.*;
import ai.nexusone.service.AutonomousEnterpriseValuationIntelligenceService;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/autonomous-enterprise-valuation-intelligence")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseValuationIntelligenceController {
    private final AutonomousEnterpriseValuationIntelligenceService service;
    public AutonomousEnterpriseValuationIntelligenceController(AutonomousEnterpriseValuationIntelligenceService service){this.service=service;}
    @PostMapping("/generate") public ResponseEntity<AutonomousEnterpriseValuationIntelligenceResponse> generate(@Valid @RequestBody AutonomousEnterpriseValuationIntelligenceRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));}
    @GetMapping("/{id}") public AutonomousEnterpriseValuationIntelligenceResponse get(@PathVariable Long id){return service.getById(id);}
    @GetMapping("/history") public Page<AutonomousEnterpriseValuationIntelligenceResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable pageable){return service.getAll(pageable);}
    @GetMapping("/search") public Page<AutonomousEnterpriseValuationIntelligenceResponse> search(@RequestParam(required=false) String keyword,@RequestParam(required=false) AutonomousEnterpriseValuationIntelligencePriority priority,@RequestParam(required=false) AutonomousEnterpriseValuationIntelligenceStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable pageable){return service.search(keyword,priority,status,pageable);}
}
