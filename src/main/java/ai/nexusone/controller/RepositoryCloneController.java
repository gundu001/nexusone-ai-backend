package ai.nexusone.controller;

import ai.nexusone.service.CloneService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/repositories")
@CrossOrigin(origins = "*")
public class RepositoryCloneController {

    private final CloneService cloneService;

    public RepositoryCloneController(CloneService cloneService) {
        this.cloneService = cloneService;
    }

    @PostMapping("/clone")
    public String cloneRepository(
            @RequestParam("repoUrl") String repoUrl) {

        return cloneService.cloneRepository(repoUrl);
    }
}
