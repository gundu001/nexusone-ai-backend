package ai.nexusone.exception;

public class CeoIntelligenceNotFoundException extends RuntimeException {

    public CeoIntelligenceNotFoundException(Long id) {
        super("CEO intelligence report not found: " + id);
    }
}
