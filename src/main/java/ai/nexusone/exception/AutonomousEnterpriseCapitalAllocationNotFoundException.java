package ai.nexusone.exception;

public class AutonomousEnterpriseCapitalAllocationNotFoundException extends RuntimeException {

    public AutonomousEnterpriseCapitalAllocationNotFoundException(Long id) {
        super("Capital allocation report not found: " + id);
    }
}
