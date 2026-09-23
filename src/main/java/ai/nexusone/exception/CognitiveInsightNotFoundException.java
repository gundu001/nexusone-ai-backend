package ai.nexusone.exception;

public class CognitiveInsightNotFoundException extends RuntimeException {

    public CognitiveInsightNotFoundException(Long id) {
        super("Cognitive insight not found: " + id);
    }
}
