package ai.nexusone.exception;

public class IndustryEcosystemIntelligenceNotFoundException extends RuntimeException {
    public IndustryEcosystemIntelligenceNotFoundException(Long id) {
        super("Industry ecosystem intelligence report not found: " + id);
    }
}
