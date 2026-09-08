package ai.nexusone.service;

import ai.nexusone.dto.RiskAnalysisRequest;
import ai.nexusone.entity.DeploymentDecision;
import ai.nexusone.entity.RiskLevel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RiskScoringService {

    public int score(RiskAnalysisRequest request) {

        int score = 0;

        score += request.getCriticalFindings() * 35;
        score += request.getHighFindings() * 20;
        score += request.getMediumFindings() * 8;
        score += request.getLowFindings() * 2;

        score += Math.min(
                request.getTestsFailed() * 5,
                20
        );

        if (!request.isBuildSuccessful()) {

            score += 25;
        }

        if (!request.isDockerfilePresent()) {

            score += 5;
        }

        if (!request.isHealthcheckPresent()) {

            score += 5;
        }

        return Math.min(score, 100);
    }

    public RiskLevel level(int score) {

        if (score >= 80) {

            return RiskLevel.CRITICAL;
        }

        if (score >= 60) {

            return RiskLevel.HIGH;
        }

        if (score >= 30) {

            return RiskLevel.MEDIUM;
        }

        return RiskLevel.LOW;
    }

    public DeploymentDecision decision(
            RiskLevel level,
            RiskAnalysisRequest request) {

        if (request.getCriticalFindings() > 0
                || !request.isBuildSuccessful()
                || level == RiskLevel.CRITICAL) {

            return DeploymentDecision.BLOCKED;
        }

        if (level == RiskLevel.HIGH) {

            return DeploymentDecision.MANUAL_APPROVAL_REQUIRED;
        }

        if (level == RiskLevel.MEDIUM) {

            return DeploymentDecision.DEPLOY_WITH_CAUTION;
        }

        return DeploymentDecision.SAFE_TO_DEPLOY;
    }

    public List<String> recommendations(
            RiskAnalysisRequest request) {

        List<String> recommendations =
                new ArrayList<>();

        if (request.getCriticalFindings() > 0) {

            recommendations.add(
                    "Resolve all critical findings."
            );
        }

        if (request.getHighFindings() > 0) {

            recommendations.add(
                    "Remediate high findings."
            );
        }

        if (request.getTestsFailed() > 0) {

            recommendations.add(
                    "Fix failing tests."
            );
        }

        if (!request.isBuildSuccessful()) {

            recommendations.add(
                    "Restore a successful build."
            );
        }

        if (!request.isDockerfilePresent()) {

            recommendations.add(
                    "Add a Dockerfile."
            );
        }

        if (!request.isHealthcheckPresent()) {

            recommendations.add(
                    "Add a health check."
            );
        }

        if (recommendations.isEmpty()) {

            recommendations.add(
                    "No blocking risks detected."
            );
        }

        return recommendations;
    }
}