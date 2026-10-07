package ai.nexusone.specification;
import ai.nexusone.entity.AutonomousEnterpriseInvestmentStrategy;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.Locale;
public final class AutonomousEnterpriseInvestmentStrategySpecification {
 private AutonomousEnterpriseInvestmentStrategySpecification() {}
 public static Specification<AutonomousEnterpriseInvestmentStrategy> filter(String keyword, AutonomousEnterpriseInvestmentStrategyPriority priority, AutonomousEnterpriseInvestmentStrategyStatus status) {
  return (root,query,cb) -> { var p=cb.conjunction();
   if(keyword!=null&&!keyword.isBlank()) p=cb.and(p,cb.like(cb.lower(root.get("title")),"%"+keyword.trim().toLowerCase(Locale.ROOT)+"%"));
   if(priority!=null) p=cb.and(p,cb.equal(root.get("priority"),priority));
   if(status!=null) p=cb.and(p,cb.equal(root.get("status"),status)); return p; };
 }
}
