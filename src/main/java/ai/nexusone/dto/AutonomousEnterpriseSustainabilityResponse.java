package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseSustainabilityResponse(
 Long id,String title,String sustainabilityVision,String esgStrategy,String carbonReductionPlan,
 String energyEfficiencyProgram,String circularEconomyStrategy,String sustainableSupplyChain,
 String climateRiskAssessment,String regulatoryCompliancePlan,Double visionScore,Double esgScore,
 Double carbonReductionScore,Double energyEfficiencyScore,Double circularEconomyScore,
 Double supplyChainScore,Double climateRiskScore,Double complianceScore,Double sustainabilityScore,
 AutonomousEnterpriseSustainabilityPriority priority,AutonomousEnterpriseSustainabilityStatus status,
 String createdBy,String reviewedBy,LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,
 String publishedBy,LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
