package ai.nexusone.specification;
import ai.nexusone.entity.AutonomousEnterpriseResilience;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
public final class AutonomousEnterpriseResilienceSpecification {
 private AutonomousEnterpriseResilienceSpecification(){}
 public static Specification<AutonomousEnterpriseResilience> filter(String k,AutonomousEnterpriseResiliencePriority p,AutonomousEnterpriseResilienceStatus s){return(root,q,cb)->{var x=cb.conjunction();if(k!=null&&!k.isBlank())x=cb.and(x,cb.like(cb.lower(root.get("title")),"%"+k.toLowerCase()+"%"));if(p!=null)x=cb.and(x,cb.equal(root.get("priority"),p));if(s!=null)x=cb.and(x,cb.equal(root.get("status"),s));return x;};}
}
