package ai.nexusone.service;

import ai.nexusone.dto.DashboardSummary;
import ai.nexusone.repository.RepositoryRepository;
import ai.nexusone.repository.RepositoryScanResultRepository;
import ai.nexusone.repository.RiskAnalysisResultRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final RepositoryRepository repositoryRepository;

    private final RepositoryScanResultRepository scanRepository;

    private final RiskAnalysisResultRepository riskRepository;

    public DashboardService(
            RepositoryRepository repositoryRepository,
            RepositoryScanResultRepository scanRepository,
            RiskAnalysisResultRepository riskRepository) {

        this.repositoryRepository = repositoryRepository;
        this.scanRepository = scanRepository;
        this.riskRepository = riskRepository;
    }

    public DashboardSummary getSummary() {

        DashboardSummary summary =
                new DashboardSummary();

        summary.setTotalRepositories(
                repositoryRepository.count());

        summary.setTotalScans(
                scanRepository.count());

        summary.setTotalRiskAnalyses(
                riskRepository.count());

        summary.setLowRiskRepositories(
                riskRepository.countBySeverity("LOW"));

        summary.setMediumRiskRepositories(
                riskRepository.countBySeverity("MEDIUM"));

        summary.setHighRiskRepositories(
                riskRepository.countBySeverity("HIGH"));

        double averageRiskScore =
                riskRepository.findAll()
                        .stream()
                        .mapToInt(
                                r -> r.getRiskScore())
                        .average()
                        .orElse(0);

        summary.setAverageRiskScore(
                averageRiskScore);

        return summary;
    }
}