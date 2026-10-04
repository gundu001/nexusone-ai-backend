package ai.nexusone.exception;
public class AutonomousEnterpriseTransformationNotFoundException extends RuntimeException {
 public AutonomousEnterpriseTransformationNotFoundException(Long id){super("Transformation report not found: "+id);}
}
