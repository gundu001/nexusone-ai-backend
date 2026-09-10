package ai.nexusone.service;

import ai.nexusone.dto.AdvisorHistoryResponse;
import ai.nexusone.dto.AdvisorResult;
import ai.nexusone.dto.RiskRow;
import ai.nexusone.entity.AdvisorResultEntity;
import ai.nexusone.exception.RiskAnalysisNotFoundException;
import ai.nexusone.repository.AdvisorResultRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class AdvisorService {

    private final AdvisorResultRepository repository;
    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public AdvisorService(
            AdvisorResultRepository repository,
            JdbcTemplate jdbcTemplate,
            ObjectMapper objectMapper
    ) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public AdvisorResult generate(String repoName) {
        String repositoryName = validateRepositoryName(repoName);
        RiskRow risk = findLatestRisk(repositoryName);

        int score = clamp(risk.getRiskScore());
        String severity = resolveSeverity(risk.getSeverity(), score);
        List<String> risks = parseList(risk.getFindings());
        List<String> recommendations = buildRecommendations(risks, score);
        List<String> strengths = buildStrengths(risks, score);

        AdvisorResultEntity entity = new AdvisorResultEntity();
        entity.setRepositoryName(risk.getRepositoryName());
        entity.setRiskScore(score);
        entity.setSeverity(severity);
        entity.setConfidenceScore(Math.max(0, 100 - score));
        entity.setDeploymentDecision(resolveDecision(score));
        entity.setRecommendations(toJson(recommendations));
        entity.setStrengths(toJson(strengths));
        entity.setRisks(toJson(risks));
        entity.setGeneratedAt(LocalDateTime.now());

        AdvisorResultEntity savedEntity = repository.save(entity);
        return toAdvisorResult(savedEntity);
    }

    @Transactional
    public AdvisorResult simulate(String repoName) {
        return generate(repoName);
    }

    @Transactional(readOnly = true)
    public AdvisorResult latest(String repoName) {
        String repositoryName = validateRepositoryName(repoName);

        AdvisorResultEntity entity = repository
                .findTopByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(repositoryName)
                .orElseThrow(() -> new RiskAnalysisNotFoundException(
                        "No advisor result found for repository: " + repositoryName
                ));

        return toAdvisorResult(entity);
    }

    @Transactional(readOnly = true)
    public List<AdvisorHistoryResponse> history() {
        return repository.findTop20ByOrderByGeneratedAtDesc()
                .stream()
                .map(this::toHistoryResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<AdvisorHistoryResponse> history(String repoName) {
        String repositoryName = validateRepositoryName(repoName);

        return repository
                .findTop20ByRepositoryNameIgnoreCaseOrderByGeneratedAtDesc(repositoryName)
                .stream()
                .map(this::toHistoryResponse)
                .toList();
    }

    private RiskRow findLatestRisk(String repositoryName) {
        String sql = """
                SELECT repository_name, risk_score, severity, findings
                FROM risk_analysis_results
                WHERE LOWER(repository_name) = LOWER(?)
                ORDER BY id DESC
                LIMIT 1
                """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    (resultSet, rowNumber) -> new RiskRow(
                            resultSet.getString("repository_name"),
                            resultSet.getInt("risk_score"),
                            resultSet.getString("severity"),
                            resultSet.getString("findings")
                    ),
                    repositoryName
            );
        } catch (EmptyResultDataAccessException exception) {
            throw new RiskAnalysisNotFoundException(
                    "No persisted risk analysis found for repository: "
                            + repositoryName
                            + ". Run risk analysis first."
            );
        }
    }

    private String validateRepositoryName(String repoName) {
        if (repoName == null || repoName.isBlank()) {
            throw new IllegalArgumentException("repoName is required.");
        }
        return repoName.trim();
    }

    private int clamp(Integer score) {
        if (score == null) {
            return 0;
        }
        return Math.max(0, Math.min(100, score));
    }

    private String resolveSeverity(String severity, int score) {
        if (severity != null && !severity.isBlank()) {
            return severity.trim().toUpperCase(Locale.ROOT);
        }

        if (score <= 25) {
            return "LOW";
        }
        if (score <= 50) {
            return "MEDIUM";
        }
        if (score <= 75) {
            return "HIGH";
        }
        return "CRITICAL";
    }

    private String resolveDecision(int score) {
        if (score <= 25) {
            return "SAFE_TO_DEPLOY";
        }
        if (score <= 50) {
            return "DEPLOY_WITH_CAUTION";
        }
        if (score <= 75) {
            return "MANUAL_REVIEW_REQUIRED";
        }
        return "BLOCK_DEPLOYMENT";
    }

    private List<String> buildRecommendations(List<String> risks, int score) {
        List<String> recommendations = new ArrayList<>();
        String combinedRisks = String.join(" ", risks).toLowerCase(Locale.ROOT);

        if (combinedRisks.contains("docker")) {
            recommendations.add("Add and validate a production-ready Dockerfile.");
        }
        if (combinedRisks.contains("kubernetes") || combinedRisks.contains("k8s")) {
            recommendations.add("Add Kubernetes deployment and service manifests.");
        }
        if (combinedRisks.contains("pipeline")
                || combinedRisks.contains("ci/cd")
                || combinedRisks.contains("jenkins")) {
            recommendations.add("Configure an automated CI/CD validation pipeline.");
        }
        if (combinedRisks.contains("rollback")) {
            recommendations.add("Define and test a deployment rollback strategy.");
        }
        if (combinedRisks.contains("health") || combinedRisks.contains("actuator")) {
            recommendations.add("Expose and validate an application health endpoint.");
        }

        if (score > 75) {
            recommendations.add("Block deployment until critical findings are resolved.");
        } else if (score > 50) {
            recommendations.add("Require manual approval before deployment.");
        } else if (score > 25) {
            recommendations.add("Review all findings before approval.");
        } else {
            recommendations.add("Proceed through the normal deployment approval workflow.");
        }

        return recommendations.stream().distinct().toList();
    }

    private List<String> buildStrengths(List<String> risks, int score) {
        List<String> strengths = new ArrayList<>();
        String combinedRisks = String.join(" ", risks).toLowerCase(Locale.ROOT);

        if (!combinedRisks.contains("docker")) {
            strengths.add("No Docker-related risk finding was reported.");
        }
        if (!combinedRisks.contains("kubernetes") && !combinedRisks.contains("k8s")) {
            strengths.add("No Kubernetes-related risk finding was reported.");
        }
        if (score <= 25) {
            strengths.add("Risk score is in the low-risk range.");
        }
        if (strengths.isEmpty()) {
            strengths.add("A completed risk assessment is available for advisor review.");
        }

        return strengths.stream().distinct().toList();
    }

    private List<String> parseList(String raw) {
        if (raw == null || raw.isBlank()) {
            return new ArrayList<>();
        }

        String trimmed = raw.trim();

        if (trimmed.startsWith("[") && trimmed.endsWith("]")) {
            try {
                return objectMapper.readValue(
                        trimmed,
                        new TypeReference<List<String>>() { }
                );
            } catch (JsonProcessingException ignored) {
                // Continue with plain-text parsing.
            }
        }

        String cleaned = trimmed
                .replace("[", "")
                .replace("]", "")
                .replace("\"", "");

        return Arrays.stream(cleaned.split("\\r?\\n|;|\\|"))
                .map(String::trim)
                .filter(value -> !value.isBlank())
                .toList();
    }

    private String toJson(List<String> values) {
        try {
            return objectMapper.writeValueAsString(values);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "Cannot serialize advisor data.",
                    exception
            );
        }
    }

    private List<String> fromJson(String raw) {
        if (raw == null || raw.isBlank()) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(
                    raw,
                    new TypeReference<List<String>>() { }
            );
        } catch (JsonProcessingException exception) {
            return List.of(raw);
        }
    }

    private AdvisorResult toAdvisorResult(AdvisorResultEntity entity) {
        return new AdvisorResult(
                entity.getId(),
                entity.getRepositoryName(),
                entity.getRiskScore(),
                entity.getSeverity(),
                entity.getConfidenceScore(),
                entity.getDeploymentDecision(),
                fromJson(entity.getRecommendations()),
                fromJson(entity.getStrengths()),
                fromJson(entity.getRisks()),
                entity.getGeneratedAt()
        );
    }

    private AdvisorHistoryResponse toHistoryResponse(AdvisorResultEntity entity) {
        return new AdvisorHistoryResponse(
                entity.getId(),
                entity.getRepositoryName(),
                entity.getRiskScore(),
                entity.getSeverity(),
                entity.getConfidenceScore(),
                entity.getDeploymentDecision(),
                entity.getGeneratedAt()
        );
    }
}
