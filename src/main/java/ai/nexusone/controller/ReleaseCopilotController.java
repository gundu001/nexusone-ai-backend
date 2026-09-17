package ai.nexusone.controller;
import ai.nexusone.dto.request.ReleaseCopilotQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.ReleaseCopilotAnalysis;
import ai.nexusone.service.ReleaseCopilotService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/release-copilot") @CrossOrigin(origins="http://localhost:5173")
public class ReleaseCopilotController {
 private final ReleaseCopilotService service; public ReleaseCopilotController(ReleaseCopilotService service){this.service=service;}
 @GetMapping("/overview") public ReleaseCopilotOverviewResponse overview(){return service.overview();}
 @GetMapping("/releases") public List<ReleaseCopilotReleaseResponse> releases(){return service.releases();}
 @GetMapping("/releases/{id}") public ReleaseCopilotReleaseResponse release(@PathVariable Long id){return service.release(id);}
 @GetMapping("/releases/{id}/assessment") public ReleaseCopilotAssessmentResponse assessment(@PathVariable Long id){return service.assessment(id);}
 @PostMapping("/ask") public ReleaseCopilotAnswerResponse ask(@Valid @RequestBody ReleaseCopilotQueryRequest request){return service.ask(request);}
 @GetMapping("/history") public Page<ReleaseCopilotAnalysis> history(@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="20")int size){return service.history(page,size);}
 @GetMapping("/history/{id}") public Page<ReleaseCopilotAnalysis> historyByRelease(@PathVariable Long id,@RequestParam(defaultValue="0")int page,@RequestParam(defaultValue="20")int size){return service.historyByRelease(id,page,size);}
}
