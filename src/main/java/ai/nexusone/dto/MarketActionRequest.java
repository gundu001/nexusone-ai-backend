package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record MarketActionRequest(@NotBlank String actionBy) {}
