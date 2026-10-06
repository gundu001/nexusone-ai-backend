package ai.nexusone.controller;

import ai.nexusone.dto.AutonomousEnterprisePortfolioOptimizationRequest;
import ai.nexusone.dto.AutonomousEnterprisePortfolioOptimizationResponse;
import ai.nexusone.enums.AutonomousEnterprisePortfolioOptimizationPriority;
import ai.nexusone.enums.AutonomousEnterprisePortfolioOptimizationStatus;
import ai.nexusone.service.AutonomousEnterprisePortfolioOptimizationService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autonomous-enterprise-portfolio-optimization")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterprisePortfolioOptimizationController {

    private final AutonomousEnterprisePortfolioOptimizationService service;

    public AutonomousEnterprisePortfolioOptimizationController(
            AutonomousEnterprisePortfolioOptimizationService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<AutonomousEnterprisePortfolioOptimizationResponse> generate(
            @Valid @RequestBody AutonomousEnterprisePortfolioOptimizationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.generate(request));
    }

    @GetMapping("/{id}")
    public AutonomousEnterprisePortfolioOptimizationResponse get(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @GetMapping("/history")
    public Page<AutonomousEnterprisePortfolioOptimizationResponse> history(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC)
            Pageable pageable) {

        return service.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<AutonomousEnterprisePortfolioOptimizationResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false)
            AutonomousEnterprisePortfolioOptimizationPriority priority,
            @RequestParam(required = false)
            AutonomousEnterprisePortfolioOptimizationStatus status,
            @PageableDefault(size = 20)
            Pageable pageable) {

        return service.search(
                keyword,
                priority,
                status,
                pageable);
    }
}