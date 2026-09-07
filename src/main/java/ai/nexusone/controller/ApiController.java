package ai.nexusone.controller;
import ai.nexusone.dto.*; import ai.nexusone.entity.*; import ai.nexusone.service.*; import jakarta.validation.Valid; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api")
public class ApiController {
 private final ApplicationService apps;private final DeploymentService deployments;private final GitHubService github;
 public ApiController(ApplicationService a,DeploymentService d,GitHubService g){apps=a;deployments=d;github=g;}
 @GetMapping("/apps") List<ApplicationEntity> apps(){return apps.findAll();}
 @PostMapping("/apps") @ResponseStatus(HttpStatus.CREATED) ApplicationEntity register(@Valid @RequestBody RegisterApplicationRequest r){return apps.register(r);}
 @PostMapping("/apps/analyze") AnalysisResponse analyze(@Valid @RequestBody AnalyzeRequest r){return apps.analysis(r);}
 @DeleteMapping("/apps/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) void delete(@PathVariable String id){apps.delete(id);}
 @GetMapping("/github/repos") List<GitHubRepositoryResponse> repos(){return github.repositories();}
 @GetMapping("/github/repos/{owner}/{repo}/branches") List<String> branches(@PathVariable String owner,@PathVariable String repo){return github.branches(owner,repo);}
 @GetMapping("/deployments") List<DeploymentEntity> deployments(){return deployments.findAll();}
 @PostMapping("/deployments/simulate") RiskResponse simulate(@Valid @RequestBody DeploymentRequest r){return deployments.simulate(r);}
 @PostMapping("/deployments") @ResponseStatus(HttpStatus.CREATED) DeploymentEntity deploy(@Valid @RequestBody DeploymentRequest r){return deployments.create(r);}
 @PostMapping("/deployments/{id}/rollback") DeploymentEntity rollback(@PathVariable String id){return deployments.rollback(id);}
 @GetMapping("/dashboard") Map<String,Object> dashboard(){List<ApplicationEntity>a=apps.findAll();List<DeploymentEntity>d=deployments.findAll();return Map.of("applications",a.size(),"healthy",a.stream().filter(x->"HEALTHY".equals(x.getStatus())).count(),"deployments",d.size(),"aiFindings",a.stream().filter(x->x.getHealthScore()<100).count());}
}
