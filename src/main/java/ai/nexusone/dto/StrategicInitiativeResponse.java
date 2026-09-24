package ai.nexusone.dto;
import ai.nexusone.enums.*;
import java.time.LocalDateTime;
public record StrategicInitiativeResponse(Long id,String title,StrategicType strategicType,String businessObjective,String currentState,String targetState,String businessImpact,String recommendation,Double alignmentScore,Double valueScore,Double riskScore,Double priorityScore,StrategicStatus status,String createdBy,String reviewedBy,LocalDateTime reviewedAt,String approvedBy,LocalDateTime approvedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
