package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record AutonomousEnterpriseMetaIntelligenceActionRequest(@NotBlank String actionBy) {}
