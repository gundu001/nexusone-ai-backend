package ai.nexusone.dto.response;
import java.time.LocalDateTime;
public record ResiliencePlanResponse(Long id,String applicationName,String environment,String primaryProvider,String primaryRegion,String secondaryProvider,String secondaryRegion,int rtoMinutes,int rpoMinutes,double readinessScore,String status,LocalDateTime createdAt) {}
