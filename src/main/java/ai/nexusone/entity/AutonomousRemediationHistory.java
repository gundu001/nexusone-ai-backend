package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "autonomous_remediation_history", indexes = {
        @Index(name = "idx_remediation_application", columnList = "application"),
        @Index(name = "idx_remediation_status", columnList = "status"),
        @Index(name = "idx_remediation_created_at", columnList = "created_at")
})
public class AutonomousRemediationHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String application;

    @Column(name = "action_type", nullable = false, length = 40)
    private String actionType;

    @Column(name = "selected_action", nullable = false, length = 500)
    private String selectedAction;

    @Column(nullable = false, length = 40)
    private String status;

    @Column(name = "dry_run", nullable = false)
    private boolean dryRun;

    @Column(name = "approved_by", length = 120)
    private String approvedBy;

    @Column(name = "requested_by", length = 120)
    private String requestedBy;

    @Column(nullable = false, length = 2000)
    private String result;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    protected AutonomousRemediationHistory() {}

    public AutonomousRemediationHistory(String application, String actionType, String selectedAction,
            String status, boolean dryRun, String approvedBy, String requestedBy, String result) {
        this.application = application;
        this.actionType = actionType;
        this.selectedAction = selectedAction;
        this.status = status;
        this.dryRun = dryRun;
        this.approvedBy = approvedBy;
        this.requestedBy = requestedBy;
        this.result = result;
    }

    @PrePersist
    void prePersist() {
        if (createdAt == null) createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public String getApplication() { return application; }
    public String getActionType() { return actionType; }
    public String getSelectedAction() { return selectedAction; }
    public String getStatus() { return status; }
    public boolean isDryRun() { return dryRun; }
    public String getApprovedBy() { return approvedBy; }
    public String getRequestedBy() { return requestedBy; }
    public String getResult() { return result; }
    public Instant getCreatedAt() { return createdAt; }
}
