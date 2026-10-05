package ai.nexusone.exception;
public class AutonomousEnterpriseResilienceNotFoundException extends RuntimeException { public AutonomousEnterpriseResilienceNotFoundException(Long id){super("Resilience report not found: "+id);} }
