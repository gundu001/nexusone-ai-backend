package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseInvestmentStrategyResponse(
 Long id, String title, String investmentStrategyVision, String strategicInvestmentRoadmap,
 String growthInvestmentStrategy, String innovationInvestmentStrategy,
 String technologyInvestmentStrategy, String marketExpansionStrategy,
 String riskDiversificationStrategy, String executiveInvestmentDecision,
 Double strategicInvestmentScore, Double expectedGrowthScore,
 Double innovationPotentialScore, Double marketOpportunityScore,
 Double technologyReadinessScore, Double riskDiversificationScore,
 Double investmentEfficiencyScore, Double executionConfidenceScore,
 Double enterpriseInvestmentStrategyScore,
 AutonomousEnterpriseInvestmentStrategyPriority priority,
 AutonomousEnterpriseInvestmentStrategyStatus status, String createdBy,
 LocalDateTime createdAt, LocalDateTime updatedAt) {}
