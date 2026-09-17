package ai.nexusone.dto.response;

import java.time.Instant;
import java.util.List;

public record DeliveryCopilotAnswerResponse(
        String repositoryName,
        String question,
        String answer,
        double confidence,
        List<String> sources,
        Instant generatedAt
) {}
