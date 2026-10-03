package ai.nexusone.exception;
public class AutonomousEnterpriseCollectiveIntelligenceNotFoundException extends RuntimeException {
 public AutonomousEnterpriseCollectiveIntelligenceNotFoundException(Long id){super("Collective Intelligence report not found: "+id);}
}
