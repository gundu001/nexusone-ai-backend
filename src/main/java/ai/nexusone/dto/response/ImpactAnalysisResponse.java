package ai.nexusone.dto.response;
public record ImpactAnalysisResponse(Long impactId,Long changeId,String application,int affectedServices,int estimatedAffectedUsers,int estimatedDowntimeMinutes,String businessImpact,String rollbackComplexity) {}
