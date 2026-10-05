package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseResilienceResponse(
 Long id,String title,String resilienceVision,String businessContinuityPlan,
 String disasterRecoveryStrategy,String cyberResilienceFramework,String infrastructureResilienceModel,
 String workforceResilienceProgram,String thirdPartyResilienceAssessment,
 String supplyChainResilienceStrategy,String operationalResilienceBlueprint,
 Double visionScore,Double continuityScore,Double recoveryScore,Double cyberScore,
 Double infrastructureScore,Double workforceScore,Double thirdPartyScore,
 Double supplyChainScore,Double operationalScore,Double resilienceScore,
 AutonomousEnterpriseResiliencePriority priority,AutonomousEnterpriseResilienceStatus status,
 String createdBy,String reviewedBy,LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,
 String publishedBy,LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
