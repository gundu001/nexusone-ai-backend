package ai.nexusone.specification;
import ai.nexusone.entity.AutonomousEnterpriseQuantumIntelligence;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
public final class AutonomousEnterpriseQuantumIntelligenceSpecification {
 private AutonomousEnterpriseQuantumIntelligenceSpecification(){}
 public static Specification<AutonomousEnterpriseQuantumIntelligence> filter(String keyword,AutonomousEnterpriseQuantumIntelligencePriority priority,AutonomousEnterpriseQuantumIntelligenceStatus status){
  return (root,q,cb)->{var p=cb.conjunction();if(keyword!=null&&!keyword.isBlank())p=cb.and(p,cb.like(cb.lower(root.get("title")),"%"+keyword.toLowerCase()+"%"));if(priority!=null)p=cb.and(p,cb.equal(root.get("priority"),priority));if(status!=null)p=cb.and(p,cb.equal(root.get("status"),status));return p;};
 }
}
