package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
public record JenkinsTriggerRequest(@NotBlank String repositoryName,@NotBlank String jobName,@NotBlank String triggeredBy,Map<String,String> parameters) {}
