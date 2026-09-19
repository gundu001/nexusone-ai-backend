package ai.nexusone.dto.request;
import jakarta.validation.constraints.*;
public record BusinessImpactRequest(@NotBlank String applicationName,@NotBlank String incidentType,@Min(0) int affectedServices,@Min(0) long estimatedUsersAffected,@DecimalMin("0.0") double estimatedFinancialImpact) {}
