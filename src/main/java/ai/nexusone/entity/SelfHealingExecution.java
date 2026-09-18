package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "self_healing_execution", indexes = {
        @Index(name = "idx_self_healing_application", columnList = "application"),
        @Index(name = "idx_self_healing_executed_at", columnList = "executed_at")
})
public class SelfHealingExecution {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100) private String application;
    @Column(nullable = false, length = 500) private String action;
    @Column(name = "dry_run", nullable = false) private boolean dryRun;
    @Column(nullable = false, length = 40) private String status;
    @Column(nullable = false, length = 2000) private String result;
    @Column(name = "executed_at", nullable = false, updatable = false) private Instant executedAt;

    protected SelfHealingExecution() {}
    public SelfHealingExecution(String application, String action, boolean dryRun, String status, String result) {
        this.application=application; this.action=action; this.dryRun=dryRun; this.status=status; this.result=result;
    }
    @PrePersist void prePersist(){ if(executedAt==null) executedAt=Instant.now(); }
    public Long getId(){return id;} public String getApplication(){return application;}
    public String getAction(){return action;} public boolean isDryRun(){return dryRun;}
    public String getStatus(){return status;} public String getResult(){return result;}
    public Instant getExecutedAt(){return executedAt;}
}
