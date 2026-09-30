package ai.nexusone.exception;

public class GlobalEconomicIntelligenceNotFoundException extends RuntimeException {
    public GlobalEconomicIntelligenceNotFoundException(Long id) {
        super("Global economic intelligence report not found: " + id);
    }
}
