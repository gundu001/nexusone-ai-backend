package ai.nexusone.entity;
import ai.nexusone.enums.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="future_prediction_intelligence_reports", indexes={
 @Index(name="idx_future_prediction_status",columnList="status"),
 @Index(name="idx_future_prediction_priority",columnList="priority"),
 @Index(name="idx_future_prediction_created",columnList="created_at")})
public class FuturePredictionIntelligenceReport {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String title;
 @Column(nullable=false,columnDefinition="TEXT") private String futureOutlook;
 @Column(nullable=false,columnDefinition="TEXT") private String technologyTrendForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String marketEvolutionForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String customerBehaviorForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String economicForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String supplyChainForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String workforceForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String cyberThreatForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String regulatoryForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String industryDisruptionForecast;
 @Column(nullable=false,columnDefinition="TEXT") private String strategicRecommendations;
 @Column(nullable=false) private Double technologyPredictionScore;
 @Column(nullable=false) private Double marketPredictionScore;
 @Column(nullable=false) private Double customerPredictionScore;
 @Column(nullable=false) private Double economicPredictionScore;
 @Column(nullable=false) private Double supplyChainPredictionScore;
 @Column(nullable=false) private Double workforcePredictionScore;
 @Column(nullable=false) private Double cyberThreatPredictionScore;
 @Column(nullable=false) private Double regulatoryPredictionScore;
 @Column(nullable=false) private Double industryDisruptionPredictionScore;
 @Column(nullable=false) private Double futurePredictionIntelligenceScore;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private FuturePredictionPriority priority;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private FuturePredictionStatus status=FuturePredictionStatus.GENERATED;
 @Column(nullable=false) private String createdBy;
 private String reviewedBy,decidedBy,publishedBy;
 private LocalDateTime reviewedAt,decidedAt,publishedAt,createdAt,updatedAt;
 @PrePersist void create(){createdAt=updatedAt=LocalDateTime.now();if(status==null)status=FuturePredictionStatus.GENERATED;}
 @PreUpdate void update(){updatedAt=LocalDateTime.now();}
 public Long getId(){return id;}
 public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getFutureOutlook(){return futureOutlook;} public void setFutureOutlook(String v){futureOutlook=v;}
 public String getTechnologyTrendForecast(){return technologyTrendForecast;} public void setTechnologyTrendForecast(String v){technologyTrendForecast=v;}
 public String getMarketEvolutionForecast(){return marketEvolutionForecast;} public void setMarketEvolutionForecast(String v){marketEvolutionForecast=v;}
 public String getCustomerBehaviorForecast(){return customerBehaviorForecast;} public void setCustomerBehaviorForecast(String v){customerBehaviorForecast=v;}
 public String getEconomicForecast(){return economicForecast;} public void setEconomicForecast(String v){economicForecast=v;}
 public String getSupplyChainForecast(){return supplyChainForecast;} public void setSupplyChainForecast(String v){supplyChainForecast=v;}
 public String getWorkforceForecast(){return workforceForecast;} public void setWorkforceForecast(String v){workforceForecast=v;}
 public String getCyberThreatForecast(){return cyberThreatForecast;} public void setCyberThreatForecast(String v){cyberThreatForecast=v;}
 public String getRegulatoryForecast(){return regulatoryForecast;} public void setRegulatoryForecast(String v){regulatoryForecast=v;}
 public String getIndustryDisruptionForecast(){return industryDisruptionForecast;} public void setIndustryDisruptionForecast(String v){industryDisruptionForecast=v;}
 public String getStrategicRecommendations(){return strategicRecommendations;} public void setStrategicRecommendations(String v){strategicRecommendations=v;}
 public Double getTechnologyPredictionScore(){return technologyPredictionScore;} public void setTechnologyPredictionScore(Double v){technologyPredictionScore=v;}
 public Double getMarketPredictionScore(){return marketPredictionScore;} public void setMarketPredictionScore(Double v){marketPredictionScore=v;}
 public Double getCustomerPredictionScore(){return customerPredictionScore;} public void setCustomerPredictionScore(Double v){customerPredictionScore=v;}
 public Double getEconomicPredictionScore(){return economicPredictionScore;} public void setEconomicPredictionScore(Double v){economicPredictionScore=v;}
 public Double getSupplyChainPredictionScore(){return supplyChainPredictionScore;} public void setSupplyChainPredictionScore(Double v){supplyChainPredictionScore=v;}
 public Double getWorkforcePredictionScore(){return workforcePredictionScore;} public void setWorkforcePredictionScore(Double v){workforcePredictionScore=v;}
 public Double getCyberThreatPredictionScore(){return cyberThreatPredictionScore;} public void setCyberThreatPredictionScore(Double v){cyberThreatPredictionScore=v;}
 public Double getRegulatoryPredictionScore(){return regulatoryPredictionScore;} public void setRegulatoryPredictionScore(Double v){regulatoryPredictionScore=v;}
 public Double getIndustryDisruptionPredictionScore(){return industryDisruptionPredictionScore;} public void setIndustryDisruptionPredictionScore(Double v){industryDisruptionPredictionScore=v;}
 public Double getFuturePredictionIntelligenceScore(){return futurePredictionIntelligenceScore;} public void setFuturePredictionIntelligenceScore(Double v){futurePredictionIntelligenceScore=v;}
 public FuturePredictionPriority getPriority(){return priority;} public void setPriority(FuturePredictionPriority v){priority=v;}
 public FuturePredictionStatus getStatus(){return status;} public void setStatus(FuturePredictionStatus v){status=v;}
 public String getCreatedBy(){return createdBy;} public void setCreatedBy(String v){createdBy=v;}
 public String getReviewedBy(){return reviewedBy;} public void setReviewedBy(String v){reviewedBy=v;}
 public String getDecidedBy(){return decidedBy;} public void setDecidedBy(String v){decidedBy=v;}
 public String getPublishedBy(){return publishedBy;} public void setPublishedBy(String v){publishedBy=v;}
 public LocalDateTime getReviewedAt(){return reviewedAt;} public void setReviewedAt(LocalDateTime v){reviewedAt=v;}
 public LocalDateTime getDecidedAt(){return decidedAt;} public void setDecidedAt(LocalDateTime v){decidedAt=v;}
 public LocalDateTime getPublishedAt(){return publishedAt;} public void setPublishedAt(LocalDateTime v){publishedAt=v;}
 public LocalDateTime getCreatedAt(){return createdAt;}
 public LocalDateTime getUpdatedAt(){return updatedAt;}
}
