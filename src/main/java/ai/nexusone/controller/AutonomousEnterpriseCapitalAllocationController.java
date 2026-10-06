package ai.nexusone.controller;

import ai.nexusone.dto.AutonomousEnterpriseCapitalAllocationRequest;
import ai.nexusone.dto.AutonomousEnterpriseCapitalAllocationResponse;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationPriority;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationStatus;
import ai.nexusone.service.AutonomousEnterpriseCapitalAllocationService;
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
@RequestMapping("/api/autonomous-enterprise-capital-allocation")
@CrossOrigin(origins = "http://localhost:5173")
public class AutonomousEnterpriseCapitalAllocationController {

    private final AutonomousEnterpriseCapitalAllocationService service;

    public AutonomousEnterpriseCapitalAllocationController(
            AutonomousEnterpriseCapitalAllocationService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<AutonomousEnterpriseCapitalAllocationResponse> generate(
            @Valid @RequestBody AutonomousEnterpriseCapitalAllocationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.generate(request));
    }

    @GetMapping("/{id}")
    public AutonomousEnterpriseCapitalAllocationResponse get(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/history")
    public Page<AutonomousEnterpriseCapitalAllocationResponse> history(
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/search")
    public Page<AutonomousEnterpriseCapitalAllocationResponse> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false)
            AutonomousEnterpriseCapitalAllocationPriority priority,
            @RequestParam(required = false)
            AutonomousEnterpriseCapitalAllocationStatus status,
            @PageableDefault(
                    size = 20,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {
        return service.search(keyword, priority, status, pageable);
    }
}
