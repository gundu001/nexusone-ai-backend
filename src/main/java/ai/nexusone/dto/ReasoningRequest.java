package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
public record ReasoningRequest(@NotBlank String query,String context,List<String> signals) {}
