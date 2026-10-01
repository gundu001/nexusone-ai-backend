package ai.nexusone.entity;

import ai.nexusone.enums.DigitalBoardMemberPriority;
import ai.nexusone.enums.DigitalBoardMemberStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "digital_board_member_intelligence_reports", indexes = {
        @Index(name = "idx_dbmi_status", columnList = "status"),
        @Index(name = "idx_dbmi_priority", columnList = "priority"),
        @Index(name = "idx_dbmi_created", columnList = "created_at")
})
public class DigitalBoardMemberIntelligenceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) private String title;
    @Column(nullable = false, columnDefinition = "TEXT") private String boardAgenda;
    @Column(nullable = false, columnDefinition = "TEXT") private String strategicOversight;
    @Column(nullable = false, columnDefinition = "TEXT") private String financialStewardship;
    @Column(nullable = false, columnDefinition = "TEXT") private String riskGovernance;
    @Column(nullable = false, columnDefinition = "TEXT") private String technologyOversight;
    @Column(nullable = false, columnDefinition = "TEXT") private String aiGovernance;
    @Column(nullable = false, columnDefinition = "TEXT") private String cybersecurityOversight;
    @Column(nullable = false, columnDefinition = "TEXT") private String stakeholderAlignment;
    @Column(nullable = false, columnDefinition = "TEXT") private String executiveAccountability;
    @Column(nullable = false, columnDefinition = "TEXT") private String boardRecommendations;

    @Column(nullable = false) private Double boardAgendaScore;
    @Column(nullable = false) private Double strategicOversightScore;
    @Column(nullable = false) private Double financialStewardshipScore;
    @Column(nullable = false) private Double riskGovernanceScore;
    @Column(nullable = false) private Double technologyOversightScore;
    @Column(nullable = false) private Double aiGovernanceScore;
    @Column(nullable = false) private Double cybersecurityOversightScore;
    @Column(nullable = false) private Double stakeholderAlignmentScore;
    @Column(nullable = false) private Double executiveAccountabilityScore;
    @Column(nullable = false) private Double digitalBoardMemberIntelligenceScore;

    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private DigitalBoardMemberPriority priority;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private DigitalBoardMemberStatus status = DigitalBoardMemberStatus.GENERATED;

    @Column(nullable = false) private String createdBy;
    private String reviewedBy;
    private String decidedBy;
    private String publishedBy;
    private LocalDateTime reviewedAt;
    private LocalDateTime decidedAt;
    private LocalDateTime publishedAt;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false) private LocalDateTime updatedAt;

    @PrePersist
    void create() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
        if (status == null) status = DigitalBoardMemberStatus.GENERATED;
    }

    @PreUpdate
    void update() { updatedAt = LocalDateTime.now(); }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String value) { title = value; }
    public String getBoardAgenda() { return boardAgenda; }
    public void setBoardAgenda(String value) { boardAgenda = value; }
    public String getStrategicOversight() { return strategicOversight; }
    public void setStrategicOversight(String value) { strategicOversight = value; }
    public String getFinancialStewardship() { return financialStewardship; }
    public void setFinancialStewardship(String value) { financialStewardship = value; }
    public String getRiskGovernance() { return riskGovernance; }
    public void setRiskGovernance(String value) { riskGovernance = value; }
    public String getTechnologyOversight() { return technologyOversight; }
    public void setTechnologyOversight(String value) { technologyOversight = value; }
    public String getAiGovernance() { return aiGovernance; }
    public void setAiGovernance(String value) { aiGovernance = value; }
    public String getCybersecurityOversight() { return cybersecurityOversight; }
    public void setCybersecurityOversight(String value) { cybersecurityOversight = value; }
    public String getStakeholderAlignment() { return stakeholderAlignment; }
    public void setStakeholderAlignment(String value) { stakeholderAlignment = value; }
    public String getExecutiveAccountability() { return executiveAccountability; }
    public void setExecutiveAccountability(String value) { executiveAccountability = value; }
    public String getBoardRecommendations() { return boardRecommendations; }
    public void setBoardRecommendations(String value) { boardRecommendations = value; }
    public Double getBoardAgendaScore() { return boardAgendaScore; }
    public void setBoardAgendaScore(Double value) { boardAgendaScore = value; }
    public Double getStrategicOversightScore() { return strategicOversightScore; }
    public void setStrategicOversightScore(Double value) { strategicOversightScore = value; }
    public Double getFinancialStewardshipScore() { return financialStewardshipScore; }
    public void setFinancialStewardshipScore(Double value) { financialStewardshipScore = value; }
    public Double getRiskGovernanceScore() { return riskGovernanceScore; }
    public void setRiskGovernanceScore(Double value) { riskGovernanceScore = value; }
    public Double getTechnologyOversightScore() { return technologyOversightScore; }
    public void setTechnologyOversightScore(Double value) { technologyOversightScore = value; }
    public Double getAiGovernanceScore() { return aiGovernanceScore; }
    public void setAiGovernanceScore(Double value) { aiGovernanceScore = value; }
    public Double getCybersecurityOversightScore() { return cybersecurityOversightScore; }
    public void setCybersecurityOversightScore(Double value) { cybersecurityOversightScore = value; }
    public Double getStakeholderAlignmentScore() { return stakeholderAlignmentScore; }
    public void setStakeholderAlignmentScore(Double value) { stakeholderAlignmentScore = value; }
    public Double getExecutiveAccountabilityScore() { return executiveAccountabilityScore; }
    public void setExecutiveAccountabilityScore(Double value) { executiveAccountabilityScore = value; }
    public Double getDigitalBoardMemberIntelligenceScore() { return digitalBoardMemberIntelligenceScore; }
    public void setDigitalBoardMemberIntelligenceScore(Double value) { digitalBoardMemberIntelligenceScore = value; }
    public DigitalBoardMemberPriority getPriority() { return priority; }
    public void setPriority(DigitalBoardMemberPriority value) { priority = value; }
    public DigitalBoardMemberStatus getStatus() { return status; }
    public void setStatus(DigitalBoardMemberStatus value) { status = value; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String value) { createdBy = value; }
    public String getReviewedBy() { return reviewedBy; }
    public void setReviewedBy(String value) { reviewedBy = value; }
    public String getDecidedBy() { return decidedBy; }
    public void setDecidedBy(String value) { decidedBy = value; }
    public String getPublishedBy() { return publishedBy; }
    public void setPublishedBy(String value) { publishedBy = value; }
    public LocalDateTime getReviewedAt() { return reviewedAt; }
    public void setReviewedAt(LocalDateTime value) { reviewedAt = value; }
    public LocalDateTime getDecidedAt() { return decidedAt; }
    public void setDecidedAt(LocalDateTime value) { decidedAt = value; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime value) { publishedAt = value; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
