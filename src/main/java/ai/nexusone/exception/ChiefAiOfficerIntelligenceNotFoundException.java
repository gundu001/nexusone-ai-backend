package ai.nexusone.exception;

public class ChiefAiOfficerIntelligenceNotFoundException extends RuntimeException {
    public ChiefAiOfficerIntelligenceNotFoundException(Long id) {
        super("Chief AI Officer intelligence report not found: " + id);
    }
}
