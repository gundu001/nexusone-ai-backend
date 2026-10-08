package ai.nexusone.specification;
import ai.nexusone.entity.AutonomousEnterpriseValuationIntelligence;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.Locale;
public final class AutonomousEnterpriseValuationIntelligenceSpecification {
    private AutonomousEnterpriseValuationIntelligenceSpecification() {}
    public static Specification<AutonomousEnterpriseValuationIntelligence> filter(String keyword, AutonomousEnterpriseValuationIntelligencePriority priority, AutonomousEnterpriseValuationIntelligenceStatus status) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();
            if (keyword != null && !keyword.isBlank()) {
                String value = "%" + keyword.trim().toLowerCase(Locale.ROOT) + "%";
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("title")), value));
            }
            if (priority != null) predicate = cb.and(predicate, cb.equal(root.get("priority"), priority));
            if (status != null) predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            return predicate;
        };
    }
}
