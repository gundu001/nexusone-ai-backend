package ai.nexusone.exception;
public class AutonomousEnterpriseOmniIntelligenceNotFoundException extends RuntimeException {
 public AutonomousEnterpriseOmniIntelligenceNotFoundException(Long id){super("Omni-intelligence report not found: "+id);}
}
