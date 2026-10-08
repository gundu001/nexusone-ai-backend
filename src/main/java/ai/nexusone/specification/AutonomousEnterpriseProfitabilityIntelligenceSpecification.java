package ai.nexusone.specification;

import ai.nexusone.entity.AutonomousEnterpriseProfitabilityIntelligence;
import ai.nexusone.enums.*;
import org.springframework.data.jpa.domain.Specification;
import java.util.Locale;

public final class AutonomousEnterpriseProfitabilityIntelligenceSpecification {
    private AutonomousEnterpriseProfitabilityIntelligenceSpecification() {}

    public static Specification<AutonomousEnterpriseProfitabilityIntelligence> filter(String keyword, AutonomousEnterpriseProfitabilityIntelligencePriority priority, AutonomousEnterpriseProfitabilityIntelligenceStatus status) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();
            if (keyword != null && !keyword.isBlank()) {
                String value = "%" + keyword.trim().toLowerCase(Locale.ROOT) + "%";
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), value));
            }
            if (priority != null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("priority"), priority));
            if (status != null) predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("status"), status));
            return predicate;
        };
    }
}
