package ai.nexusone.dto.response;
import java.time.Instant; import java.util.List;
public record ServiceHealthResponse(String serviceName,String environment,String status,double healthScore,long latencyMs,double errorRate,double throughputRps,double availability,String dependency,Instant observedAt,List<String> signals) {}
