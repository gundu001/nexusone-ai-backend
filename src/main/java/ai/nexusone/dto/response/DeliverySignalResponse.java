package ai.nexusone.dto.response;

public record DeliverySignalResponse(
        String code,
        String severity,
        String title,
        String description,
        String recommendation
) {}
