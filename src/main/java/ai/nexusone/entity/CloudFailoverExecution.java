package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cloud_failover_executions")
public class CloudFailoverExecution {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private Long planId;
    @Column(nullable = false) private String applicationName;
    @Column(nullable = false) private String sourceProvider;
    @Column(nullable = false) private String targetProvider;
    @Column(nullable = false) private String action;
    @Column(nullable = false) private String status;
    private boolean dryRun;
    private String initiatedBy;
    @Column(length=1000) private String message;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    @PrePersist void prePersist(){if(startedAt==null)startedAt=LocalDateTime.now();}
    public Long getId(){return id;} public void setId(Long v){id=v;}
    public Long getPlanId(){return planId;} public void setPlanId(Long v){planId=v;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getSourceProvider(){return sourceProvider;} public void setSourceProvider(String v){sourceProvider=v;}
    public String getTargetProvider(){return targetProvider;} public void setTargetProvider(String v){targetProvider=v;}
    public String getAction(){return action;} public void setAction(String v){action=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public boolean isDryRun(){return dryRun;} public void setDryRun(boolean v){dryRun=v;}
    public String getInitiatedBy(){return initiatedBy;} public void setInitiatedBy(String v){initiatedBy=v;}
    public String getMessage(){return message;} public void setMessage(String v){message=v;}
    public LocalDateTime getStartedAt(){return startedAt;} public void setStartedAt(LocalDateTime v){startedAt=v;}
    public LocalDateTime getCompletedAt(){return completedAt;} public void setCompletedAt(LocalDateTime v){completedAt=v;}
}
