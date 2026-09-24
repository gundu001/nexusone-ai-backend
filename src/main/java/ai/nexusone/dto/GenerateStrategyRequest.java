package ai.nexusone.dto;
import ai.nexusone.enums.StrategicType;
import jakarta.validation.constraints.*;
import java.util.List;
public record GenerateStrategyRequest(@NotBlank String title,@NotNull StrategicType strategicType,@NotBlank String businessObjective,@NotBlank String currentState,@NotBlank String targetState,List<String> evidence,@NotBlank String createdBy) {}
