package ai.nexusone.exception;
public class AutonomousEnterpriseValuationIntelligenceNotFoundException extends RuntimeException {
    public AutonomousEnterpriseValuationIntelligenceNotFoundException(Long id) { super("Valuation intelligence report not found: " + id); }
}
