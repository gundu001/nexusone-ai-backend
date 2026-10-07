package ai.nexusone.specification;

import ai.nexusone.entity.AutonomousEnterpriseBusinessStrategy;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyPriority;
import ai.nexusone.enums.AutonomousEnterpriseBusinessStrategyStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public final class AutonomousEnterpriseBusinessStrategySpecification {

    private AutonomousEnterpriseBusinessStrategySpecification() {
    }

    public static Specification<AutonomousEnterpriseBusinessStrategy> filter(
            String keyword,
            AutonomousEnterpriseBusinessStrategyPriority priority,
            AutonomousEnterpriseBusinessStrategyStatus status) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();
            if (keyword != null && !keyword.isBlank()) {
                String value = "%" + keyword.trim().toLowerCase(Locale.ROOT) + "%";
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), value));
            }
            if (priority != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("priority"), priority));
            }
            if (status != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("status"), status));
            }
            return predicate;
        };
    }
}
