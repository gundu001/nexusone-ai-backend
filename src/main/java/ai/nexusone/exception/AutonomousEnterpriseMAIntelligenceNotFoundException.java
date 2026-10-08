package ai.nexusone.exception;
public class AutonomousEnterpriseMAIntelligenceNotFoundException extends RuntimeException {
    public AutonomousEnterpriseMAIntelligenceNotFoundException(Long id) { super("M&A intelligence report not found: " + id); }
}
