package ai.nexusone.controller;

import ai.nexusone.entity.RepositoryEntity;
import ai.nexusone.repository.RepositoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repositories")
@CrossOrigin(origins = "*")
public class RepositoryController {

    private final RepositoryRepository repositoryRepository;

    public RepositoryController(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @PostMapping
    public RepositoryEntity save(@RequestBody RepositoryEntity repository) {
        return repositoryRepository.save(repository);
    }

    @GetMapping
    public List<RepositoryEntity> findAll() {
        return repositoryRepository.findAll();
    }
}