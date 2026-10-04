package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record AutonomousEnterpriseTransformationResponse(
 Long id,String title,String transformationVision,String transformationRoadmap,
 String operatingModelTransformation,String workforceTransformation,
 String processTransformation,String technologyTransformation,String customerTransformation,
 String transformationRiskAssessment,Double visionScore,Double roadmapScore,
 Double operatingModelScore,Double workforceScore,Double processScore,
 Double technologyScore,Double customerScore,Double executionScore,
 Double transformationScore,AutonomousEnterpriseTransformationPriority priority,
 AutonomousEnterpriseTransformationStatus status,String createdBy,String reviewedBy,
 LocalDateTime reviewedAt,String decidedBy,LocalDateTime decidedAt,String publishedBy,
 LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
