package ai.nexusone.exception;

public class AutonomousEnterpriseProfitabilityIntelligenceNotFoundException extends RuntimeException {
    public AutonomousEnterpriseProfitabilityIntelligenceNotFoundException(Long id) {
        super("Profitability intelligence report not found: " + id);
    }
}
