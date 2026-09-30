package ai.nexusone.exception;

public class EnterpriseRiskIntelligenceNotFoundException extends RuntimeException {
    public EnterpriseRiskIntelligenceNotFoundException(Long id) {
        super("Enterprise risk intelligence report not found: " + id);
    }
}
