package ai.nexusone.dto;
import ai.nexusone.enums.BoardroomBriefingStatus;
import ai.nexusone.enums.BoardroomPriority;
import java.time.LocalDateTime;
public record BoardroomBriefingResponse(
        Long id, String title, String executiveSummary, String strategicAgenda,
        String boardRecommendation, Double expectedRoi, Double strategicAlignment,
        Double riskExposure, Double boardroomScore, BoardroomPriority priority,
        BoardroomBriefingStatus status, String createdBy, String reviewedBy,
        LocalDateTime reviewedAt, String decidedBy, LocalDateTime decidedAt,
        String executedBy, LocalDateTime executedAt, LocalDateTime createdAt,
        LocalDateTime updatedAt) {}
