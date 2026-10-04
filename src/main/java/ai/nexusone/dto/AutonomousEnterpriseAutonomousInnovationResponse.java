package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseAutonomousInnovationResponse(
 Long id,String title,String innovationOpportunity,String marketDisruptionStrategy,
 String newRevenueStrategy,String technologyInnovationStrategy,String businessModelInnovation,
 String customerExperienceInnovation,String innovationRiskAssessment,String autonomousInnovationRecommendations,
 Double innovationPotentialScore,Double marketOpportunityScore,Double technologyFeasibilityScore,
 Double revenuePotentialScore,Double businessModelScore,Double customerValueScore,
 Double riskReadinessScore,Double executionReadinessScore,Double autonomousInnovationScore,
 AutonomousEnterpriseAutonomousInnovationPriority priority,
 AutonomousEnterpriseAutonomousInnovationStatus status,String createdBy,String reviewedBy,
 LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,String publishedBy,
 LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
