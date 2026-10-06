package ai.nexusone.specification;

import ai.nexusone.entity.AutonomousEnterpriseCapitalAllocation;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationPriority;
import ai.nexusone.enums.AutonomousEnterpriseCapitalAllocationStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.Locale;

public final class AutonomousEnterpriseCapitalAllocationSpecification {

    private AutonomousEnterpriseCapitalAllocationSpecification() {
    }

    public static Specification<AutonomousEnterpriseCapitalAllocation> filter(
            String keyword,
            AutonomousEnterpriseCapitalAllocationPriority priority,
            AutonomousEnterpriseCapitalAllocationStatus status) {

        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (keyword != null && !keyword.isBlank()) {
                String searchValue = "%" + keyword.trim().toLowerCase(Locale.ROOT) + "%";
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), searchValue)
                );
            }
            if (priority != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("priority"), priority)
                );
            }
            if (status != null) {
                predicate = criteriaBuilder.and(
                        predicate,
                        criteriaBuilder.equal(root.get("status"), status)
                );
            }
            return predicate;
        };
    }
}
