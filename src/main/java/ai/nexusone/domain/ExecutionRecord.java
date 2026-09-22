package ai.nexusone.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "execution_fabric_records", indexes = {
    @Index(name = "idx_execution_status", columnList = "status"),
    @Index(name = "idx_execution_created", columnList = "createdAt"),
    @Index(name = "uk_execution_idempotency", columnList = "idempotencyKey", unique = true)
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ExecutionRecord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long decisionId;
    @Column(nullable=false) private String executionName;
    @Column(nullable=false) private String applicationName;
    @Column(nullable=false) private String environment;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private ActionType actionType;
    @Column(nullable=false) private String targetType;
    @Column(nullable=false) private String targetName;
    @Column(nullable=false) private String requestedBy;
    private String approvalReference;
    @Column(nullable=false, unique=true, length=120) private String idempotencyKey;
    @Lob private String parameters;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private ExecutionStatus status;
    private Integer progressPercentage;
    @Column(length=1200) private String resultMessage;
    @Column(length=1200) private String errorMessage;
    private Long parentExecutionId;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist void prePersist() {
        createdAt = updatedAt = LocalDateTime.now();
        if (status == null) status = ExecutionStatus.PENDING;
        if (progressPercentage == null) progressPercentage = 0;
    }
    @PreUpdate void preUpdate() { updatedAt = LocalDateTime.now(); }
}
