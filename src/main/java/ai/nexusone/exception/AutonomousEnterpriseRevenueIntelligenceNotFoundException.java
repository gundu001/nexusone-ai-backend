package ai.nexusone.exception;

public class AutonomousEnterpriseRevenueIntelligenceNotFoundException extends RuntimeException {
    public AutonomousEnterpriseRevenueIntelligenceNotFoundException(Long id) {
        super("Revenue intelligence report not found: " + id);
    }
}
