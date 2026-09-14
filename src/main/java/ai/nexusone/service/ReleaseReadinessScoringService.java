package ai.nexusone.service;

import ai.nexusone.dto.response.ReleaseGateResponse;
import ai.nexusone.dto.response.ReleaseReadinessOverviewResponse;
import ai.nexusone.dto.response.ReleaseReadinessRecommendationResponse;
import ai.nexusone.dto.response.ReleaseReadinessScoreResponse;
import ai.nexusone.dto.response.ReleaseReadinessTrendResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseReadinessScoringService {

    public ReleaseReadinessOverviewResponse getOverview() {
        return new ReleaseReadinessOverviewResponse(24, 16, 5, 3, 84.5, 31, 5, 94.2);
    }

    public List<ReleaseReadinessScoreResponse> getScores() {
        return List.of(
                new ReleaseReadinessScoreResponse(5401L, "NexusOne Backend", "5.4.0", "PRODUCTION", 94.0, 91.0, 96.0, 88.0, 93.0, 92.4, "READY", "APPROVE"),
                new ReleaseReadinessScoreResponse(5402L, "Risk Engine", "3.8.1", "PRODUCTION", 88.0, 84.0, 91.0, 74.0, 86.0, 84.6, "CONDITIONAL", "REVIEW"),
                new ReleaseReadinessScoreResponse(5403L, "Release Orchestrator", "5.7.2", "STAGING", 92.0, 89.0, 90.0, 82.0, 91.0, 88.8, "READY", "APPROVE"),
                new ReleaseReadinessScoreResponse(5404L, "Executive Dashboard", "5.2.4", "PRODUCTION", 86.0, 78.0, 89.0, 90.0, 84.0, 85.4, "CONDITIONAL", "REVIEW"),
                new ReleaseReadinessScoreResponse(5405L, "Jenkins Integration", "4.6.3", "PRODUCTION", 76.0, 68.0, 71.0, 58.0, 73.0, 69.2, "BLOCKED", "REJECT")
        );
    }

    public List<ReleaseGateResponse> getGates() {
        return List.of(
                new ReleaseGateResponse(5411L, 5401L, "NexusOne Backend", "Unit Test Coverage", "QUALITY", 91.0, 80.0, "PERCENT", "PASSED", true, "Coverage exceeds the production threshold."),
                new ReleaseGateResponse(5412L, 5401L, "NexusOne Backend", "Critical Vulnerabilities", "SECURITY", 0.0, 0.0, "COUNT", "PASSED", true, "No critical vulnerability is detected."),
                new ReleaseGateResponse(5413L, 5402L, "Risk Engine", "Change Risk", "RISK", 74.0, 70.0, "SCORE", "FAILED", true, "Predicted change risk is above the approval threshold."),
                new ReleaseGateResponse(5414L, 5403L, "Release Orchestrator", "Integration Test Success", "QUALITY", 96.0, 90.0, "PERCENT", "PASSED", true, "Integration test success meets the requirement."),
                new ReleaseGateResponse(5415L, 5404L, "Executive Dashboard", "Test Coverage", "QUALITY", 78.0, 80.0, "PERCENT", "WARNING", false, "Coverage is slightly below the preferred threshold."),
                new ReleaseGateResponse(5416L, 5405L, "Jenkins Integration", "Security Score", "SECURITY", 71.0, 85.0, "SCORE", "FAILED", true, "Security score does not meet the production threshold."),
                new ReleaseGateResponse(5417L, 5405L, "Jenkins Integration", "Rollback Validation", "OPERATIONS", 0.0, 1.0, "BOOLEAN", "FAILED", true, "Rollback validation has not completed successfully.")
        );
    }

    public ReleaseReadinessTrendResponse getTrends() {
        return new ReleaseReadinessTrendResponse(84.5, 79.0, 6.96, List.of(
                new ReleaseReadinessTrendResponse.TrendPoint("Mon", 78.0, 3, 1, 89.0),
                new ReleaseReadinessTrendResponse.TrendPoint("Tue", 80.0, 3, 1, 91.0),
                new ReleaseReadinessTrendResponse.TrendPoint("Wed", 82.0, 4, 1, 92.0),
                new ReleaseReadinessTrendResponse.TrendPoint("Thu", 85.0, 3, 0, 95.0),
                new ReleaseReadinessTrendResponse.TrendPoint("Fri", 84.5, 3, 0, 94.2)
        ));
    }

    public List<ReleaseReadinessRecommendationResponse> getRecommendations() {
        return List.of(
                new ReleaseReadinessRecommendationResponse(5421L, 5405L, "CRITICAL", "Jenkins Integration", "SECURITY", "The release does not satisfy mandatory security and rollback gates.", "Resolve security findings, complete rollback validation, and rerun readiness scoring."),
                new ReleaseReadinessRecommendationResponse(5422L, 5402L, "HIGH", "Risk Engine", "CHANGE_RISK", "Predicted change risk exceeds the configured production threshold.", "Require approval and use a monitored canary rollout with a verified rollback plan."),
                new ReleaseReadinessRecommendationResponse(5423L, 5404L, "MEDIUM", "Executive Dashboard", "TEST_COVERAGE", "Test coverage is below the preferred readiness threshold.", "Add tests for changed modules before final production approval."),
                new ReleaseReadinessRecommendationResponse(5424L, 5401L, "LOW", "NexusOne Backend", "DELIVERY", "All mandatory release gates have passed.", "Approve the release and continue monitored blue-green deployment.")
        );
    }
}
