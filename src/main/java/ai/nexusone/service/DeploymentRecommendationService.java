package ai.nexusone.service;

import ai.nexusone.dto.DeploymentRecommendationResult;
import ai.nexusone.dto.RiskAnalysisResult;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DeploymentRecommendationService {

    private final RiskAnalysisService riskAnalysisService;

    public DeploymentRecommendationService(
            RiskAnalysisService riskAnalysisService) {

        this.riskAnalysisService = riskAnalysisService;
    }

    public DeploymentRecommendationResult getRecommendation(
            String repoName)
            throws IOException {

        RiskAnalysisResult risk =
                riskAnalysisService.analyzeRisk(repoName);

        DeploymentRecommendationResult result =
                new DeploymentRecommendationResult();

        result.setRepositoryName(
                risk.getRepositoryName());

        result.setRiskScore(
                risk.getRiskScore());

        result.setSeverity(
                risk.getSeverity());

        if ("LOW".equals(risk.getSeverity())) {

            result.setRecommendation(
                    "SAFE_TO_DEPLOY");

            result.setMessage(
                    "Repository is safe for deployment."
            );

        } else if ("MEDIUM".equals(
                risk.getSeverity())) {

            result.setRecommendation(
                    "REVIEW_REQUIRED");

            result.setMessage(
                    "Repository should be reviewed before deployment."
            );

        } else {

            result.setRecommendation(
                    "DEPLOYMENT_BLOCKED");

            result.setMessage(
                    "Deployment should not proceed until risks are fixed."
            );
        }

        return result;
    }
}