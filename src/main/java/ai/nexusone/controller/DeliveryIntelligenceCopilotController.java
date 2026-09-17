package ai.nexusone.controller;

import ai.nexusone.dto.request.DeliveryCopilotQueryRequest;
import ai.nexusone.dto.response.DeliveryCopilotAnswerResponse;
import ai.nexusone.dto.response.DeliveryCopilotOverviewResponse;
import ai.nexusone.dto.response.DeliverySignalResponse;
import ai.nexusone.entity.DeliveryCopilotAnalysis;
import ai.nexusone.service.DeliveryIntelligenceCopilotService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/delivery-intelligence-copilot")
@CrossOrigin(origins = "http://localhost:5173")
public class DeliveryIntelligenceCopilotController {
    private final DeliveryIntelligenceCopilotService service;

    public DeliveryIntelligenceCopilotController(DeliveryIntelligenceCopilotService service) {
        this.service = service;
    }

    @GetMapping("/overview")
    public DeliveryCopilotOverviewResponse overview() { return service.overview(); }

    @GetMapping("/signals")
    public List<DeliverySignalResponse> signals() { return service.signals(); }

    @PostMapping("/ask")
    public DeliveryCopilotAnswerResponse ask(@Valid @RequestBody DeliveryCopilotQueryRequest request) {
        return service.ask(request);
    }

    @GetMapping("/history")
    public Page<DeliveryCopilotAnalysis> history(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.history(page, size);
    }

    @GetMapping("/history/{repositoryName}")
    public Page<DeliveryCopilotAnalysis> historyByRepository(
            @PathVariable String repositoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.historyByRepository(repositoryName, page, size);
    }
}
