package ai.nexusone.specification;

import ai.nexusone.entity.AutonomousEnterpriseRevenueIntelligence;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.Locale;

public final class AutonomousEnterpriseRevenueIntelligenceSpecification {
    private AutonomousEnterpriseRevenueIntelligenceSpecification() {}
    public static Specification<AutonomousEnterpriseRevenueIntelligence> filter(String keyword, AutonomousEnterpriseRevenueIntelligencePriority priority, AutonomousEnterpriseRevenueIntelligenceStatus status) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();
            if (keyword != null && !keyword.isBlank()) predicate = cb.and(predicate, cb.like(cb.lower(root.get("title")), "%" + keyword.trim().toLowerCase(Locale.ROOT) + "%"));
            if (priority != null) predicate = cb.and(predicate, cb.equal(root.get("priority"), priority));
            if (status != null) predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            return predicate;
        };
    }
}
