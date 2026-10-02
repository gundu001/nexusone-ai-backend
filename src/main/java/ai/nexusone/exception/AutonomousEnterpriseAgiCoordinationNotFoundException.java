package ai.nexusone.exception;

public class AutonomousEnterpriseAgiCoordinationNotFoundException extends RuntimeException {
    public AutonomousEnterpriseAgiCoordinationNotFoundException(Long id) { super("AGI Coordination report not found: " + id); }
}
