package ai.nexusone.controller;

import ai.nexusone.dto.AutonomousEnterpriseBusinessStrategyRequest;
import ai.nexusone.dto.AutonomousEnterpriseBusinessStrategyResponse;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyPriority;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyStatus;
import ai.nexusone.service.AutonomousEnterpriseBusinessStrategyService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autonomous-enterprise-business-strategy")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseBusinessStrategyController {

    private final AutonomousEnterpriseBusinessStrategyService service;

    public AutonomousEnterpriseBusinessStrategyController(
            AutonomousEnterpriseBusinessStrategyService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<AutonomousEnterpriseBusinessStrategyResponse> generate(
            @Valid @RequestBody AutonomousEnterpriseBusinessStrategyRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    public AutonomousEnterpriseBusinessStrategyResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/history")
    public Page<AutonomousEnterpriseBusinessStrategyResponse> history(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<AutonomousEnterpriseBusinessStrategyResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) AutonomousEnterpriseBusinessStrategyPriority priority,
            @RequestParam(required = false) AutonomousEnterpriseBusinessStrategyStatus status,
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }
}
