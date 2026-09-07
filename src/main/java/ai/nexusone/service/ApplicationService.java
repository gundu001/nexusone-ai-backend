package ai.nexusone.service;
import ai.nexusone.dto.*; import ai.nexusone.entity.ApplicationEntity; import ai.nexusone.exception.NotFoundException; import ai.nexusone.repository.ApplicationRepository; import org.springframework.stereotype.Service; import java.util.List;
@Service
public class ApplicationService {
 private final ApplicationRepository repo; private final RepositoryAnalyzerService analyzer;
 public ApplicationService(ApplicationRepository r,RepositoryAnalyzerService a){repo=r;analyzer=a;}
 public List<ApplicationEntity> findAll(){return repo.findAll();}
 public ApplicationEntity register(RegisterApplicationRequest r){return repo.findByRepositoryUrl(r.repositoryUrl()).orElseGet(()->repo.save(new ApplicationEntity(r.name(),r.technology(),r.repositoryUrl(),r.branch()==null?"main":r.branch(),"CONNECTED",100)));}
 public ApplicationEntity analyze(AnalyzeRequest r){AnalysisResponse a=analyzer.analyze(r.repositoryUrl(),r.branch());ApplicationEntity e=repo.findByRepositoryUrl(r.repositoryUrl()).orElseGet(()->new ApplicationEntity(name(r.repositoryUrl()),"Detected by analysis",r.repositoryUrl(),a.branch(),a.status(),a.healthScore()));e.setBranchName(a.branch());e.setStatus(a.status());e.setHealthScore(a.healthScore());return repo.save(e);}
 public AnalysisResponse analysis(AnalyzeRequest r){AnalysisResponse a=analyzer.analyze(r.repositoryUrl(),r.branch());ApplicationEntity e=repo.findByRepositoryUrl(r.repositoryUrl()).orElseGet(()->new ApplicationEntity(name(r.repositoryUrl()),"Detected by analysis",r.repositoryUrl(),a.branch(),a.status(),a.healthScore()));e.setBranchName(a.branch());e.setStatus(a.status());e.setHealthScore(a.healthScore());repo.save(e);return a;}
 public void delete(String id){if(!repo.existsById(id))throw new NotFoundException("Application not found: "+id);repo.deleteById(id);}
 private String name(String u){String v=u.replace(".git","");return v.substring(v.lastIndexOf('/')+1);}
}
