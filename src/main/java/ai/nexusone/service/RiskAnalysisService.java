package ai.nexusone.service;

import ai.nexusone.dto.RepositoryScanResult;
import ai.nexusone.dto.RiskAnalysisResult;
import ai.nexusone.entity.RiskAnalysisResultEntity;
import ai.nexusone.repository.RiskAnalysisResultRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RiskAnalysisService {

    private final RepositoryScannerService repositoryScannerService;

    private final RiskAnalysisResultRepository riskRepository;

    public RiskAnalysisService(
            RepositoryScannerService repositoryScannerService,
            RiskAnalysisResultRepository riskRepository) {

        this.repositoryScannerService = repositoryScannerService;
        this.riskRepository = riskRepository;
    }

    public RiskAnalysisResult analyzeRisk(String repoName)
            throws IOException {

        RepositoryScanResult scanResult =
                repositoryScannerService.scanRepository(repoName);

        RiskAnalysisResult riskResult =
                new RiskAnalysisResult();

        riskResult.setRepositoryName(repoName);

        int riskScore = 0;

        List<String> findings =
                new ArrayList<>();

        // -----------------------------
        // Risk Rules
        // -----------------------------

        if (!scanResult.isPomFilePresent()) {

            riskScore += 30;

            findings.add(
                    "pom.xml file not found."
            );
        }

        if (!scanResult.isApplicationConfigPresent()) {

            riskScore += 20;

            findings.add(
                    "Application configuration file not found."
            );
        }

        if (!scanResult.isDockerfilePresent()) {

            riskScore += 20;

            findings.add(
                    "Dockerfile not found."
            );
        }

        if (!scanResult.isReadmePresent()) {

            riskScore += 5;

            findings.add(
                    "README documentation not found."
            );
        }

        if (!scanResult.isKubernetesFilesPresent()) {

            riskScore += 10;

            findings.add(
                    "Kubernetes deployment files were not found."
            );
        }

        riskResult.setRiskScore(riskScore);

        // -----------------------------
        // Severity
        // -----------------------------

        if (riskScore <= 20) {

            riskResult.setSeverity("LOW");

        } else if (riskScore <= 50) {

            riskResult.setSeverity("MEDIUM");

        } else {

            riskResult.setSeverity("HIGH");
        }

        // -----------------------------
        // Deployment Recommendation
        // -----------------------------

        String recommendation;

        if (riskScore <= 20) {

            recommendation = "SAFE_TO_DEPLOY";

        } else if (riskScore <= 50) {

            recommendation = "DEPLOY_WITH_CAUTION";

        } else {

            recommendation = "MANUAL_REVIEW_REQUIRED";
        }

        riskResult.setRecommendation(
                recommendation
        );

        if (findings.isEmpty()) {

            findings.add(
                    "No deployment risks detected."
            );
        }

        riskResult.setFindings(findings);

        // -----------------------------
        // Persist Result
        // -----------------------------

        RiskAnalysisResultEntity entity =
                new RiskAnalysisResultEntity();

        entity.setRepositoryName(
                riskResult.getRepositoryName());

        entity.setRiskScore(
                riskResult.getRiskScore());

        entity.setSeverity(
                riskResult.getSeverity());

        entity.setRecommendation(
                recommendation);

        entity.setFindings(
                String.join(
                        ", ",
                        riskResult.getFindings()
                ));

        entity.setAnalysisTime(
                LocalDateTime.now());

        riskRepository.save(entity);

        return riskResult;
    }
}