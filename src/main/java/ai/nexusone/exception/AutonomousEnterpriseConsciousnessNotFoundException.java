package ai.nexusone.exception;

public class AutonomousEnterpriseConsciousnessNotFoundException extends RuntimeException {
    public AutonomousEnterpriseConsciousnessNotFoundException(Long id) {
        super("Autonomous Enterprise Consciousness report not found: " + id);
    }
}
