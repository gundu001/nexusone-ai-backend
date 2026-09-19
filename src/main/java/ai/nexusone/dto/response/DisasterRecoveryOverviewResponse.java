package ai.nexusone.dto.response;
public record DisasterRecoveryOverviewResponse(long assessments,long simulations,long successfulSimulations,long impactAnalyses,double averageReadinessScore,String continuityStatus) {}
