package ai.nexusone.service;

import ai.nexusone.dto.response.eoc.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EnterpriseOperationsCenterService {
    public EocOverviewResponse getOverview() {
        return new EocOverviewResponse(12, 15, 3, 3, 3, 1, 1, 92);
    }

    public EocHealthResponse getHealth() {
        return new EocHealthResponse(92, 95, 91, 94, 89);
    }

    public List<EocIncidentResponse> getIncidents() {
        return List.of(
            new EocIncidentResponse("INC-EOC-001", "checkout-service", "production", "CRITICAL", "AUTONOMOUS_REMEDIATION", "Database pool saturation", "ROLLED_BACK", Instant.parse("2026-09-19T07:27:28Z")),
            new EocIncidentResponse("INC-EOC-002", "order-worker", "production", "HIGH", "PREDICTIVE_PREVENTION", "Queue backlog growth", "PLAN_READY", Instant.parse("2026-09-19T06:10:00Z")),
            new EocIncidentResponse("INC-EOC-003", "customer-api", "staging", "CRITICAL", "OBSERVABILITY", "Readiness configuration drift", "PLAN_READY", Instant.parse("2026-09-19T06:05:00Z"))
        );
    }

    public List<EocRecommendationResponse> getRecommendations() {
        return List.of(
            new EocRecommendationResponse("REC-EOC-001", "checkout-service", "REMEDIATION", "Apply guarded traffic throttling", "CRITICAL", 0.94, "ROLLED_BACK"),
            new EocRecommendationResponse("REC-EOC-002", "order-worker", "PREVENTION", "Scale workers by two replicas", "HIGH", 0.88, "PLAN_READY"),
            new EocRecommendationResponse("REC-EOC-003", "customer-api", "RELIABILITY", "Restore validated configuration and restart", "CRITICAL", 0.96, "PLAN_READY")
        );
    }

    public EocOperationsResponse getOperations() {
        return new EocOperationsResponse(0, 1, 1, 1, 1, 4, 50);
    }
}
