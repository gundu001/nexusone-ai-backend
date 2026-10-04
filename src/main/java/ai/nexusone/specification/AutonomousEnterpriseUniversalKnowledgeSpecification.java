package ai.nexusone.specification;
import ai.nexusone.entity.AutonomousEnterpriseUniversalKnowledge;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
public final class AutonomousEnterpriseUniversalKnowledgeSpecification {
 private AutonomousEnterpriseUniversalKnowledgeSpecification(){}
 public static Specification<AutonomousEnterpriseUniversalKnowledge> filter(String keyword,AutonomousEnterpriseUniversalKnowledgePriority priority,AutonomousEnterpriseUniversalKnowledgeStatus status){
  return (root,q,cb)->{var p=cb.conjunction();if(keyword!=null&&!keyword.isBlank())p=cb.and(p,cb.like(cb.lower(root.get("title")),"%"+keyword.toLowerCase()+"%"));if(priority!=null)p=cb.and(p,cb.equal(root.get("priority"),priority));if(status!=null)p=cb.and(p,cb.equal(root.get("status"),status));return p;};
 }
}
