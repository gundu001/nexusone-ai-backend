package ai.nexusone.controller;

import ai.nexusone.dto.RecommendationDashboardSummary;
import ai.nexusone.service.RecommendationDashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        "/api/dashboard/recommendations"
)
@CrossOrigin("*")
public class RecommendationDashboardController {

    private final RecommendationDashboardService service;

    public RecommendationDashboardController(
            RecommendationDashboardService service
    ) {
        this.service = service;
    }

    @GetMapping
    public RecommendationDashboardSummary
    getSummary() {

        return service.getSummary();
    }
}