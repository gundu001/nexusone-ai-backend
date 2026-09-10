package ai.nexusone.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReleaseImpactResult {
    private Long id;
    private String repositoryName;
    private List<String> changedFiles;
    private List<String> impactedServices;
    private List<String> impactReasons;
    private List<String> recommendations;
    private Integer impactScore;
    private String impactLevel;
    private LocalDateTime analyzedAt;

    public ReleaseImpactResult() {}
    public ReleaseImpactResult(Long id, String repositoryName, List<String> changedFiles,
                               List<String> impactedServices, List<String> impactReasons,
                               List<String> recommendations, Integer impactScore,
                               String impactLevel, LocalDateTime analyzedAt) {
        this.id=id; this.repositoryName=repositoryName; this.changedFiles=changedFiles;
        this.impactedServices=impactedServices; this.impactReasons=impactReasons;
        this.recommendations=recommendations; this.impactScore=impactScore;
        this.impactLevel=impactLevel; this.analyzedAt=analyzedAt;
    }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getRepositoryName(){return repositoryName;} public void setRepositoryName(String v){repositoryName=v;}
    public List<String> getChangedFiles(){return changedFiles;} public void setChangedFiles(List<String> v){changedFiles=v;}
    public List<String> getImpactedServices(){return impactedServices;} public void setImpactedServices(List<String> v){impactedServices=v;}
    public List<String> getImpactReasons(){return impactReasons;} public void setImpactReasons(List<String> v){impactReasons=v;}
    public List<String> getRecommendations(){return recommendations;} public void setRecommendations(List<String> v){recommendations=v;}
    public Integer getImpactScore(){return impactScore;} public void setImpactScore(Integer v){impactScore=v;}
    public String getImpactLevel(){return impactLevel;} public void setImpactLevel(String v){impactLevel=v;}
    public LocalDateTime getAnalyzedAt(){return analyzedAt;} public void setAnalyzedAt(LocalDateTime v){analyzedAt=v;}
}
