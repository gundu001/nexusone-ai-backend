package ai.nexusone.service;

import ai.nexusone.dto.DashboardSummary;
import ai.nexusone.dto.RiskDistribution;
import ai.nexusone.entity.RiskAnalysisResultEntity;
import ai.nexusone.repository.RepositoryRepository;
import ai.nexusone.repository.RepositoryScanResultRepository;
import ai.nexusone.repository.RiskAnalysisResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    private final RepositoryRepository
            repositoryRepository;

    private final RepositoryScanResultRepository
            repositoryScanResultRepository;

    private final RiskAnalysisResultRepository
            riskAnalysisResultRepository;

    public DashboardService(
            RepositoryRepository repositoryRepository,
            RepositoryScanResultRepository
                    repositoryScanResultRepository,
            RiskAnalysisResultRepository
                    riskAnalysisResultRepository) {

        this.repositoryRepository =
                repositoryRepository;

        this.repositoryScanResultRepository =
                repositoryScanResultRepository;

        this.riskAnalysisResultRepository =
                riskAnalysisResultRepository;
    }

    public DashboardSummary getSummary() {

        long totalRepositories =
                repositoryRepository.count();

        long totalScans =
                repositoryScanResultRepository.count();

        long totalRiskAnalyses =
                riskAnalysisResultRepository.count();

        long lowRiskRepositories =
                riskAnalysisResultRepository
                        .countBySeverity("LOW");

        long mediumRiskRepositories =
                riskAnalysisResultRepository
                        .countBySeverity("MEDIUM");

        long highRiskRepositories =
                riskAnalysisResultRepository
                        .countBySeverity("HIGH");

        long safeToDeploy =
                riskAnalysisResultRepository
                        .countByRecommendation(
                                "SAFE_TO_DEPLOY"
                        );

        long deployWithCaution =
                riskAnalysisResultRepository
                        .countByRecommendation(
                                "DEPLOY_WITH_CAUTION"
                        );

        long manualReviewRequired =
                riskAnalysisResultRepository
                        .countByRecommendation(
                                "MANUAL_REVIEW_REQUIRED"
                        );

        Double averageRiskScoreValue =
                riskAnalysisResultRepository
                        .calculateAverageRiskScore();

        double averageRiskScore = 0.0;

        if (averageRiskScoreValue != null) {

            averageRiskScore =
                    Math.round(
                            averageRiskScoreValue * 100.0
                    ) / 100.0;
        }

        DashboardSummary summary =
                new DashboardSummary();

        summary.setTotalRepositories(
                totalRepositories
        );

        summary.setTotalScans(
                totalScans
        );

        summary.setTotalRiskAnalyses(
                totalRiskAnalyses
        );

        summary.setAverageRiskScore(
                averageRiskScore
        );

        summary.setLowRiskRepositories(
                lowRiskRepositories
        );

        summary.setMediumRiskRepositories(
                mediumRiskRepositories
        );

        summary.setHighRiskRepositories(
                highRiskRepositories
        );

        summary.setSafeToDeploy(
                safeToDeploy
        );

        summary.setDeployWithCaution(
                deployWithCaution
        );

        summary.setManualReviewRequired(
                manualReviewRequired
        );

        return summary;
    }

    public List<RiskDistribution>
    getRiskDistribution() {

        List<RiskDistribution> distribution =
                new ArrayList<>();

        distribution.add(
                new RiskDistribution(
                        "LOW",
                        riskAnalysisResultRepository
                                .countBySeverity("LOW")
                )
        );

        distribution.add(
                new RiskDistribution(
                        "MEDIUM",
                        riskAnalysisResultRepository
                                .countBySeverity("MEDIUM")
                )
        );

        distribution.add(
                new RiskDistribution(
                        "HIGH",
                        riskAnalysisResultRepository
                                .countBySeverity("HIGH")
                )
        );

        return distribution;
    }

    public List<RiskAnalysisResultEntity>
    getRecentRiskAnalyses() {

        return riskAnalysisResultRepository
                .findTop10ByOrderByAnalysisTimeDesc();
    }

    public RiskAnalysisResultEntity
    getLatestRiskAnalysis() {

        return riskAnalysisResultRepository
                .findTopByOrderByAnalysisTimeDesc()
                .orElse(null);
    }
}