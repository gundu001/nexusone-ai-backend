package ai.nexusone.repository;

import ai.nexusone.entity.AutonomousEnterpriseCapitalAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AutonomousEnterpriseCapitalAllocationRepository
        extends JpaRepository<AutonomousEnterpriseCapitalAllocation, Long>,
        JpaSpecificationExecutor<AutonomousEnterpriseCapitalAllocation> {
}
