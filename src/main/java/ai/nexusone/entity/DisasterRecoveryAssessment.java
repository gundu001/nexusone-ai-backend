package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "disaster_recovery_assessments")
public class DisasterRecoveryAssessment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false) private String applicationName;
    @Column(nullable=false) private String primaryCloud;
    @Column(nullable=false) private String recoveryCloud;
    private int readinessScore;
    private int rtoMinutes;
    private int rpoMinutes;
    private double recoveryProbability;
    @Column(nullable=false) private String riskLevel;
    @Column(nullable=false) private String backupHealth;
    @Column(nullable=false) private String failoverReadiness;
    @Column(nullable=false) private LocalDateTime assessedAt;
    @PrePersist void prePersist(){ if(assessedAt==null) assessedAt=LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getPrimaryCloud(){return primaryCloud;} public void setPrimaryCloud(String v){primaryCloud=v;}
    public String getRecoveryCloud(){return recoveryCloud;} public void setRecoveryCloud(String v){recoveryCloud=v;}
    public int getReadinessScore(){return readinessScore;} public void setReadinessScore(int v){readinessScore=v;}
    public int getRtoMinutes(){return rtoMinutes;} public void setRtoMinutes(int v){rtoMinutes=v;}
    public int getRpoMinutes(){return rpoMinutes;} public void setRpoMinutes(int v){rpoMinutes=v;}
    public double getRecoveryProbability(){return recoveryProbability;} public void setRecoveryProbability(double v){recoveryProbability=v;}
    public String getRiskLevel(){return riskLevel;} public void setRiskLevel(String v){riskLevel=v;}
    public String getBackupHealth(){return backupHealth;} public void setBackupHealth(String v){backupHealth=v;}
    public String getFailoverReadiness(){return failoverReadiness;} public void setFailoverReadiness(String v){failoverReadiness=v;}
    public LocalDateTime getAssessedAt(){return assessedAt;} public void setAssessedAt(LocalDateTime v){assessedAt=v;}
}
