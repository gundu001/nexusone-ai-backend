package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="release_impact_results", indexes={
        @Index(name="idx_release_impact_repo_time", columnList="repository_name,analyzed_at")
})
public class ReleaseImpactEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(name="repository_name", nullable=false, length=255) private String repositoryName;
    @Lob @Column(name="changed_files", columnDefinition="LONGTEXT") private String changedFiles;
    @Lob @Column(name="impacted_services", columnDefinition="LONGTEXT") private String impactedServices;
    @Lob @Column(name="impact_reasons", columnDefinition="LONGTEXT") private String impactReasons;
    @Lob @Column(name="recommendations", columnDefinition="LONGTEXT") private String recommendations;
    @Column(name="impact_score", nullable=false) private Integer impactScore;
    @Column(name="impact_level", nullable=false, length=30) private String impactLevel;
    @Column(name="analyzed_at", nullable=false) private LocalDateTime analyzedAt;
    public ReleaseImpactEntity() {}
    @PrePersist public void prePersist(){if(analyzedAt==null) analyzedAt=LocalDateTime.now();}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getRepositoryName(){return repositoryName;} public void setRepositoryName(String v){repositoryName=v;}
    public String getChangedFiles(){return changedFiles;} public void setChangedFiles(String v){changedFiles=v;}
    public String getImpactedServices(){return impactedServices;} public void setImpactedServices(String v){impactedServices=v;}
    public String getImpactReasons(){return impactReasons;} public void setImpactReasons(String v){impactReasons=v;}
    public String getRecommendations(){return recommendations;} public void setRecommendations(String v){recommendations=v;}
    public Integer getImpactScore(){return impactScore;} public void setImpactScore(Integer v){impactScore=v;}
    public String getImpactLevel(){return impactLevel;} public void setImpactLevel(String v){impactLevel=v;}
    public LocalDateTime getAnalyzedAt(){return analyzedAt;} public void setAnalyzedAt(LocalDateTime v){analyzedAt=v;}
}
