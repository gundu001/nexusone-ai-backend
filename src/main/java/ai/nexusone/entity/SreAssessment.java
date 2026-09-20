package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sre_assessments")
public class SreAssessment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String applicationName;
    private double availabilityPercent;
    private int totalRequests;
    private int failedRequests;
    private double errorRatePercent;
    private double mttrMinutes;
    private double mtbfHours;
    private double targetSloPercent;
    private double errorBudgetPercent;
    private int reliabilityScore;
    @Column(nullable = false) private String riskLevel;
    @Column(nullable = false) private String sloStatus;
    @Column(length = 2000) private String recommendation;
    @Column(nullable = false) private LocalDateTime analyzedAt;

    @PrePersist void prePersist() { if (analyzedAt == null) analyzedAt = LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public double getAvailabilityPercent(){return availabilityPercent;} public void setAvailabilityPercent(double v){availabilityPercent=v;}
    public int getTotalRequests(){return totalRequests;} public void setTotalRequests(int v){totalRequests=v;}
    public int getFailedRequests(){return failedRequests;} public void setFailedRequests(int v){failedRequests=v;}
    public double getErrorRatePercent(){return errorRatePercent;} public void setErrorRatePercent(double v){errorRatePercent=v;}
    public double getMttrMinutes(){return mttrMinutes;} public void setMttrMinutes(double v){mttrMinutes=v;}
    public double getMtbfHours(){return mtbfHours;} public void setMtbfHours(double v){mtbfHours=v;}
    public double getTargetSloPercent(){return targetSloPercent;} public void setTargetSloPercent(double v){targetSloPercent=v;}
    public double getErrorBudgetPercent(){return errorBudgetPercent;} public void setErrorBudgetPercent(double v){errorBudgetPercent=v;}
    public int getReliabilityScore(){return reliabilityScore;} public void setReliabilityScore(int v){reliabilityScore=v;}
    public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
    public String getSloStatus(){return sloStatus;} public void setSloStatus(String v){sloStatus=v;}
    public String getRecommendation(){return recommendation;} public void setRecommendation(String v){recommendation=v;}
    public LocalDateTime getAnalyzedAt(){return analyzedAt;} public void setAnalyzedAt(LocalDateTime v){analyzedAt=v;}
}
