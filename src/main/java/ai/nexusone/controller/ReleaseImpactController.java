package ai.nexusone.controller;

import ai.nexusone.dto.ReleaseImpactRequest;
import ai.nexusone.dto.ReleaseImpactResult;
import ai.nexusone.service.ReleaseImpactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/release-impact")
public class ReleaseImpactController {
    private final ReleaseImpactService service;
    public ReleaseImpactController(ReleaseImpactService service){this.service=service;}
    @PostMapping("/analyze") public ResponseEntity<ReleaseImpactResult> analyze(@RequestBody ReleaseImpactRequest request){return ResponseEntity.ok(service.analyze(request));}
    @GetMapping("/latest") public ResponseEntity<ReleaseImpactResult> latest(@RequestParam String repoName){return ResponseEntity.ok(service.latest(repoName));}
    @GetMapping("/history") public ResponseEntity<List<ReleaseImpactResult>> history(@RequestParam(required=false) String repoName){return ResponseEntity.ok(service.history(repoName));}
}
