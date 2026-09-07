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

        // Rule 1 - Missing pom.xml
        if (!scanResult.isPomFilePresent()) {

            riskScore += 30;

            findings.add(
                    "pom.xml file not found."
            );
        }

        // Rule 2 - Missing Application Config
        if (!scanResult.isApplicationConfigPresent()) {

            riskScore += 20;

            findings.add(
                    "Application configuration file not found."
            );
        }

        // Rule 3 - Missing Dockerfile
        if (!scanResult.isDockerfilePresent()) {

            riskScore += 20;

            findings.add(
                    "Dockerfile not found."
            );
        }

        // Rule 4 - Missing README
        if (!scanResult.isReadmePresent()) {

            riskScore += 5;

            findings.add(
                    "README documentation not found."
            );
        }

        // Rule 5 - Missing Kubernetes Files
        if (!scanResult.isKubernetesFilesPresent()) {

            riskScore += 10;

            findings.add(
                    "Kubernetes deployment files were not found."
            );
        }

        riskResult.setRiskScore(riskScore);

        // Severity
        if (riskScore <= 20) {

            riskResult.setSeverity("LOW");

        } else if (riskScore <= 50) {

            riskResult.setSeverity("MEDIUM");

        } else {

            riskResult.setSeverity("HIGH");
        }

        if (findings.isEmpty()) {

            findings.add(
                    "No deployment risks detected."
            );
        }

        riskResult.setFindings(findings);

        // ============================
        // Persist Risk Analysis Result
        // ============================

        RiskAnalysisResultEntity entity =
                new RiskAnalysisResultEntity();

        entity.setRepositoryName(
                riskResult.getRepositoryName());

        entity.setRiskScore(
                riskResult.getRiskScore());

        entity.setSeverity(
                riskResult.getSeverity());

        entity.setFindings(
                String.join(
                        ", ",
                        riskResult.getFindings()
                ));

        entity.setAnalysisTime(
                LocalDateTime.now());

        riskRepository.save(entity);

        // ============================

        return riskResult;
    }
}