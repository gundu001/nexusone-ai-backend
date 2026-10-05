package ai.nexusone.specification;
import ai.nexusone.entity.AutonomousEnterpriseSustainability;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
public final class AutonomousEnterpriseSustainabilitySpecification {
 private AutonomousEnterpriseSustainabilitySpecification(){}
 public static Specification<AutonomousEnterpriseSustainability> filter(String k,AutonomousEnterpriseSustainabilityPriority p,AutonomousEnterpriseSustainabilityStatus s){return(root,q,cb)->{var x=cb.conjunction();if(k!=null&&!k.isBlank())x=cb.and(x,cb.like(cb.lower(root.get("title")),"%"+k.toLowerCase()+"%"));if(p!=null)x=cb.and(x,cb.equal(root.get("priority"),p));if(s!=null)x=cb.and(x,cb.equal(root.get("status"),s));return x;};}
}
