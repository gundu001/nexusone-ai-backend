package ai.nexusone.exception;
public class AutonomousEnterpriseInvestmentStrategyNotFoundException extends RuntimeException {
 public AutonomousEnterpriseInvestmentStrategyNotFoundException(Long id) { super("Investment strategy report not found: "+id); }
}
