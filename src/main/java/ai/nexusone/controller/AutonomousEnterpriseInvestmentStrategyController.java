package ai.nexusone.controller;
import ai.nexusone.dto.*; import ai.nexusone.enums.*; import ai.nexusone.service.*; import jakarta.validation.Valid;
import org.springframework.data.domain.*; import org.springframework.data.web.PageableDefault; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/autonomous-enterprise-investment-strategy") @CrossOrigin(origins="http://localhost:5173")
public class AutonomousEnterpriseInvestmentStrategyController {
 private final AutonomousEnterpriseInvestmentStrategyService service;
 public AutonomousEnterpriseInvestmentStrategyController(AutonomousEnterpriseInvestmentStrategyService service) { this.service=service; }
 @PostMapping("/generate") public ResponseEntity<AutonomousEnterpriseInvestmentStrategyResponse> generate(@Valid @RequestBody AutonomousEnterpriseInvestmentStrategyRequest r) { return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(r)); }
 @GetMapping("/{id}") public AutonomousEnterpriseInvestmentStrategyResponse get(@PathVariable Long id) { return service.getById(id); }
 @GetMapping("/history") public Page<AutonomousEnterpriseInvestmentStrategyResponse> history(@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p) { return service.getAll(p); }
 @GetMapping("/search") public Page<AutonomousEnterpriseInvestmentStrategyResponse> search(@RequestParam(required=false) String keyword,@RequestParam(required=false) AutonomousEnterpriseInvestmentStrategyPriority priority,@RequestParam(required=false) AutonomousEnterpriseInvestmentStrategyStatus status,@PageableDefault(size=20,sort="createdAt",direction=Sort.Direction.DESC) Pageable p) { return service.search(keyword,priority,status,p); }
}
