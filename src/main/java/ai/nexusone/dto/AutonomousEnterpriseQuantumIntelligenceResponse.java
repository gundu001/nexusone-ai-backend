package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseQuantumIntelligenceResponse(
 Long id,String title,String quantumOptimizationStrategy,String quantumSimulationModel,
 String hybridQuantumClassicalOrchestration,String quantumRiskModeling,
 String quantumSecurityReadiness,String quantumDataStrategy,String enterpriseUseCases,
 String quantumIntelligenceRecommendations,Double optimizationScore,Double simulationScore,
 Double orchestrationScore,Double riskModelingScore,Double securityReadinessScore,
 Double dataReadinessScore,Double useCaseValueScore,Double adoptionReadinessScore,
 Double quantumIntelligenceScore,AutonomousEnterpriseQuantumIntelligencePriority priority,
 AutonomousEnterpriseQuantumIntelligenceStatus status,String createdBy,String reviewedBy,
 LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,String publishedBy,
 LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
