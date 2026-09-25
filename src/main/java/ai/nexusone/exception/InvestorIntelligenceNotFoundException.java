package ai.nexusone.exception;

public class InvestorIntelligenceNotFoundException extends RuntimeException {
    public InvestorIntelligenceNotFoundException(Long id) {
        super("Investor intelligence report not found: " + id);
    }
}
