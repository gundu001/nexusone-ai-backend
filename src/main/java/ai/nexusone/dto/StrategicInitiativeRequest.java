package ai.nexusone.dto;
import ai.nexusone.enums.StrategicType;
import jakarta.validation.constraints.*;
public record StrategicInitiativeRequest(@NotBlank String title,@NotNull StrategicType strategicType,@NotBlank String businessObjective,@NotBlank String currentState,@NotBlank String targetState,@NotBlank String businessImpact,@NotBlank String recommendation,@NotNull @DecimalMin("0") @DecimalMax("100") Double alignmentScore,@NotNull @DecimalMin("0") @DecimalMax("100") Double valueScore,@NotNull @DecimalMin("0") @DecimalMax("100") Double riskScore,@NotBlank String createdBy) {}
