package ai.nexusone.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity @Table(name="recovery_simulations")
public class RecoverySimulation {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String applicationName;
 @Column(nullable=false) private String scenario;
 @Column(nullable=false) private String status;
 private int estimatedRecoveryMinutes; private int achievedRtoMinutes; private int dataLossMinutes;
 @Column(length=2000) private String recoveryPlan;
 @Column(nullable=false) private LocalDateTime executedAt;
 @PrePersist void init(){if(executedAt==null)executedAt=LocalDateTime.now();}
 public Long getId(){return id;} public void setId(Long v){id=v;} public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;} public String getScenario(){return scenario;} public void setScenario(String v){scenario=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public int getEstimatedRecoveryMinutes(){return estimatedRecoveryMinutes;} public void setEstimatedRecoveryMinutes(int v){estimatedRecoveryMinutes=v;} public int getAchievedRtoMinutes(){return achievedRtoMinutes;} public void setAchievedRtoMinutes(int v){achievedRtoMinutes=v;} public int getDataLossMinutes(){return dataLossMinutes;} public void setDataLossMinutes(int v){dataLossMinutes=v;} public String getRecoveryPlan(){return recoveryPlan;} public void setRecoveryPlan(String v){recoveryPlan=v;} public LocalDateTime getExecutedAt(){return executedAt;} public void setExecutedAt(LocalDateTime v){executedAt=v;}
}
