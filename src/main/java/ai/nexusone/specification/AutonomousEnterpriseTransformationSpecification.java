package ai.nexusone.specification;
import ai.nexusone.entity.AutonomousEnterpriseTransformation;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
public final class AutonomousEnterpriseTransformationSpecification {
 private AutonomousEnterpriseTransformationSpecification(){}
 public static Specification<AutonomousEnterpriseTransformation> filter(String keyword,AutonomousEnterpriseTransformationPriority priority,AutonomousEnterpriseTransformationStatus status){
  return (root,q,cb)->{var p=cb.conjunction();if(keyword!=null&&!keyword.isBlank())p=cb.and(p,cb.like(cb.lower(root.get("title")),"%"+keyword.toLowerCase()+"%"));if(priority!=null)p=cb.and(p,cb.equal(root.get("priority"),priority));if(status!=null)p=cb.and(p,cb.equal(root.get("status"),status));return p;};
 }
}
