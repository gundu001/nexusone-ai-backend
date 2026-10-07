package ai.nexusone.exception;

public class AutonomousEnterpriseBusinessStrategyNotFoundException extends RuntimeException {

    public AutonomousEnterpriseBusinessStrategyNotFoundException(Long id) {
        super("Business strategy report not found: " + id);
    }
}
