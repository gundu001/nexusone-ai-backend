package ai.nexusone.controller;

import ai.nexusone.dto.AutonomousEnterpriseValueRealizationRequest;
import ai.nexusone.dto.AutonomousEnterpriseValueRealizationResponse;
import ai.nexusone.enums.AutonomousEnterpriseValueRealizationPriority;
import ai.nexusone.enums.AutonomousEnterpriseValueRealizationStatus;
import ai.nexusone.service.AutonomousEnterpriseValueRealizationService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/autonomous-enterprise-value-realization")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseValueRealizationController {

    private final AutonomousEnterpriseValueRealizationService service;

    public AutonomousEnterpriseValueRealizationController(
            AutonomousEnterpriseValueRealizationService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<AutonomousEnterpriseValueRealizationResponse> generate(
            @Valid @RequestBody AutonomousEnterpriseValueRealizationRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.generate(request));
    }

    @GetMapping("/{id}")
    public AutonomousEnterpriseValueRealizationResponse get(
            @PathVariable Long id) {

        return service.getById(id);
    }

    @GetMapping("/history")
    public Page<AutonomousEnterpriseValueRealizationResponse> history(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC)
            Pageable pageable) {

        return service.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<AutonomousEnterpriseValueRealizationResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false)
            AutonomousEnterpriseValueRealizationPriority priority,
            @RequestParam(required = false)
            AutonomousEnterpriseValueRealizationStatus status,
            @PageableDefault(size = 20)
            Pageable pageable) {

        return service.search(
                keyword,
                priority,
                status,
                pageable);
    }
}