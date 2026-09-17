package ai.nexusone.service;
import ai.nexusone.dto.request.ReleaseCopilotQueryRequest;
import ai.nexusone.dto.response.*;
import ai.nexusone.entity.ReleaseCopilotAnalysis;
import ai.nexusone.repository.ReleaseCopilotAnalysisRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.*;
@Service
public class ReleaseCopilotService {
 private final ReleaseCopilotAnalysisRepository history;
 private final Map<Long,ReleaseCopilotReleaseResponse> releases;
 public ReleaseCopilotService(ReleaseCopilotAnalysisRepository history){this.history=history;this.releases=seed();}
 private Map<Long,ReleaseCopilotReleaseResponse> seed(){Map<Long,ReleaseCopilotReleaseResponse> m=new LinkedHashMap<>();
  m.put(5401L,new ReleaseCopilotReleaseResponse(5401L,"NexusOne Backend","5.4.0","PRODUCTION",92.4,"READY","APPROVE",88,96,List.of("All mandatory gates passed","Test coverage is 91%","No critical vulnerability detected")));
  m.put(5402L,new ReleaseCopilotReleaseResponse(5402L,"Risk Engine","3.8.1","PRODUCTION",84.6,"CONDITIONAL","REVIEW",74,91,List.of("Change risk exceeds threshold","Human approval required","Canary rollout recommended")));
  m.put(5405L,new ReleaseCopilotReleaseResponse(5405L,"Jenkins Integration","4.6.3","PRODUCTION",69.2,"BLOCKED","REJECT",58,71,List.of("Mandatory security gate failed","Rollback validation incomplete","Release must be rescored")));
  return m;}
 public ReleaseCopilotOverviewResponse overview(){var v=releases.values();return new ReleaseCopilotOverviewResponse(v.size(),(int)v.stream().filter(r->"READY".equals(r.readinessLevel())).count(),(int)v.stream().filter(r->"CONDITIONAL".equals(r.readinessLevel())).count(),(int)v.stream().filter(r->"BLOCKED".equals(r.readinessLevel())).count(),v.stream().mapToDouble(ReleaseCopilotReleaseResponse::readinessScore).average().orElse(0));}
 public List<ReleaseCopilotReleaseResponse> releases(){return new ArrayList<>(releases.values());}
 public ReleaseCopilotReleaseResponse release(Long id){var r=releases.get(id);if(r==null)throw new NoSuchElementException("Release not found: "+id);return r;}
 public ReleaseCopilotAssessmentResponse assessment(Long id){var r=release(id);boolean blocked="BLOCKED".equals(r.readinessLevel());boolean conditional="CONDITIONAL".equals(r.readinessLevel());String risk=blocked?"CRITICAL":conditional?"HIGH":"LOW";String rec=blocked?"Do not release until mandatory gates pass.":conditional?"Proceed only with approval and monitored canary rollout.":"Approve for monitored production deployment.";return new ReleaseCopilotAssessmentResponse(id,rec,r.decision(),blocked?.96:conditional?.89:.94,risk,blocked||conditional,r.signals(),blocked?List.of("Resolve security findings","Complete rollback validation","Rerun readiness scoring"):conditional?List.of("Obtain approval","Use canary rollout","Monitor release health"):List.of("Approve release","Use monitored deployment","Verify post-deployment health"));}
 public ReleaseCopilotAnswerResponse ask(ReleaseCopilotQueryRequest q){var r=release(q.releaseId());var a=assessment(q.releaseId());String text=("Release %d for %s %s in %s has readiness %.1f%% and decision %s. Risk level is %s. Recommendation: %s Next actions: %s Human approval is %s.").formatted(r.releaseId(),r.application(),r.version(),r.environment(),r.readinessScore(),a.decision(),a.riskLevel(),a.recommendation(),String.join(", ",a.actions()),a.approvalRequired()?"required":"not required");history.save(new ReleaseCopilotAnalysis(q.releaseId(),q.question(),text,a.confidence()));return new ReleaseCopilotAnswerResponse(q.releaseId(),q.question(),text,a.confidence(),List.of("release-readiness","change-risk","quality-gates"),Instant.now());}
 public Page<ReleaseCopilotAnalysis> history(int page,int size){return history.findAll(PageRequest.of(Math.max(0,page),Math.min(100,Math.max(1,size)),Sort.by("createdAt").descending()));}
 public Page<ReleaseCopilotAnalysis> historyByRelease(Long id,int page,int size){release(id);return history.findByReleaseIdOrderByCreatedAtDesc(id,PageRequest.of(Math.max(0,page),Math.min(100,Math.max(1,size))));}
}
