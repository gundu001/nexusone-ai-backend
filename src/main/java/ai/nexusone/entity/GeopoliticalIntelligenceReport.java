package ai.nexusone.entity;
import ai.nexusone.enums.*; import jakarta.persistence.*; import java.time.LocalDateTime;
@Entity @Table(name="geopolitical_intelligence_reports",indexes={
 @Index(name="idx_geopolitical_status",columnList="status"),
 @Index(name="idx_geopolitical_priority",columnList="priority"),
 @Index(name="idx_geopolitical_created",columnList="created_at")})
public class GeopoliticalIntelligenceReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,columnDefinition="TEXT") private String geopoliticalOutlook;
 @Column(nullable=false,columnDefinition="TEXT") private String regionalConflictAnalysis;
 @Column(nullable=false,columnDefinition="TEXT") private String politicalStabilityAnalysis;
 @Column(nullable=false,columnDefinition="TEXT") private String sanctionsTradeAnalysis;
 @Column(nullable=false,columnDefinition="TEXT") private String energyResourceSecurityAnalysis;
 @Column(nullable=false,columnDefinition="TEXT") private String supplyChainGeopoliticalAnalysis;
 @Column(nullable=false,columnDefinition="TEXT") private String regulatorySovereigntyAnalysis;
 @Column(nullable=false,columnDefinition="TEXT") private String diplomaticRelationsAnalysis;
 @Column(nullable=false,columnDefinition="TEXT") private String strategicRecommendations;
 @Column(nullable=false) private Double regionalStabilityScore,politicalStabilityScore,sanctionsExposureScore,
 energySecurityScore,supplyChainResilienceScore,regulatorySovereigntyScore,diplomaticRelationsScore,
 geopoliticalIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private GeopoliticalPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private GeopoliticalStatus status=GeopoliticalStatus.GENERATED;
 @Column(nullable=false) private String createdBy; private String reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void create(){createdAt=updatedAt=LocalDateTime.now();if(status==null)status=GeopoliticalStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;} public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getGeopoliticalOutlook(){return geopoliticalOutlook;} public void setGeopoliticalOutlook(String v){geopoliticalOutlook=v;}
 public String getRegionalConflictAnalysis(){return regionalConflictAnalysis;} public void setRegionalConflictAnalysis(String v){regionalConflictAnalysis=v;}
 public String getPoliticalStabilityAnalysis(){return politicalStabilityAnalysis;} public void setPoliticalStabilityAnalysis(String v){politicalStabilityAnalysis=v;}
 public String getSanctionsTradeAnalysis(){return sanctionsTradeAnalysis;} public void setSanctionsTradeAnalysis(String v){sanctionsTradeAnalysis=v;}
 public String getEnergyResourceSecurityAnalysis(){return energyResourceSecurityAnalysis;} public void setEnergyResourceSecurityAnalysis(String v){energyResourceSecurityAnalysis=v;}
 public String getSupplyChainGeopoliticalAnalysis(){return supplyChainGeopoliticalAnalysis;} public void setSupplyChainGeopoliticalAnalysis(String v){supplyChainGeopoliticalAnalysis=v;}
 public String getRegulatorySovereigntyAnalysis(){return regulatorySovereigntyAnalysis;} public void setRegulatorySovereigntyAnalysis(String v){regulatorySovereigntyAnalysis=v;}
 public String getDiplomaticRelationsAnalysis(){return diplomaticRelationsAnalysis;} public void setDiplomaticRelationsAnalysis(String v){diplomaticRelationsAnalysis=v;}
 public String getStrategicRecommendations(){return strategicRecommendations;} public void setStrategicRecommendations(String v){strategicRecommendations=v;}
 public Double getRegionalStabilityScore(){return regionalStabilityScore;} public void setRegionalStabilityScore(Double v){regionalStabilityScore=v;}
 public Double getPoliticalStabilityScore(){return politicalStabilityScore;} public void setPoliticalStabilityScore(Double v){politicalStabilityScore=v;}
 public Double getSanctionsExposureScore(){return sanctionsExposureScore;} public void setSanctionsExposureScore(Double v){sanctionsExposureScore=v;}
 public Double getEnergySecurityScore(){return energySecurityScore;} public void setEnergySecurityScore(Double v){energySecurityScore=v;}
 public Double getSupplyChainResilienceScore(){return supplyChainResilienceScore;} public void setSupplyChainResilienceScore(Double v){supplyChainResilienceScore=v;}
 public Double getRegulatorySovereigntyScore(){return regulatorySovereigntyScore;} public void setRegulatorySovereigntyScore(Double v){regulatorySovereigntyScore=v;}
 public Double getDiplomaticRelationsScore(){return diplomaticRelationsScore;} public void setDiplomaticRelationsScore(Double v){diplomaticRelationsScore=v;}
 public Double getGeopoliticalIntelligenceScore(){return geopoliticalIntelligenceScore;} public void setGeopoliticalIntelligenceScore(Double v){geopoliticalIntelligenceScore=v;}
 public GeopoliticalPriority getPriority(){return priority;} public void setPriority(GeopoliticalPriority v){priority=v;}
 public GeopoliticalStatus getStatus(){return status;} public void setStatus(GeopoliticalStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public LocalDateTime getUpdatedAt(){return updatedAt;}
}
