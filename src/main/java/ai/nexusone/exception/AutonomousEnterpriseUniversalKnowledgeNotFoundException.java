package ai.nexusone.exception;
public class AutonomousEnterpriseUniversalKnowledgeNotFoundException extends RuntimeException {
 public AutonomousEnterpriseUniversalKnowledgeNotFoundException(Long id){super("Universal knowledge report not found: "+id);}
}
