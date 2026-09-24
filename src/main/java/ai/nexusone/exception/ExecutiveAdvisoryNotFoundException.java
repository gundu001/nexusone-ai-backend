package ai.nexusone.exception;

public class ExecutiveAdvisoryNotFoundException extends RuntimeException {
    public ExecutiveAdvisoryNotFoundException(Long id) {
        super("Executive advisory not found: " + id);
    }
}
