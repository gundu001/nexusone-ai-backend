package ai.nexusone.service;

import ai.nexusone.dto.FileFinding;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class YamlAnalyzer {
    private final SecretDetectionService secretDetectionService;

    public YamlAnalyzer(SecretDetectionService secretDetectionService) {
        this.secretDetectionService = secretDetectionService;
    }

    public List<FileFinding> analyze(Path file, String displayPath) throws IOException {
        List<String> lines = Files.readAllLines(file);
        List<FileFinding> findings = new ArrayList<>();
        String content = String.join("\n", lines).toLowerCase(Locale.ROOT);
        boolean kubernetes = content.contains("apiversion:") && content.contains("kind:");

        for (String raw : lines) {
            String line = raw.trim();
            if (line.isBlank() || line.startsWith("#") || !line.contains(":")) continue;
            int separator = line.indexOf(':');
            String key = line.substring(0, separator).trim();
            String value = line.substring(separator + 1).trim();
            findings.addAll(secretDetectionService.inspect(key, value, displayPath));

            if ("privileged".equalsIgnoreCase(key) && "true".equalsIgnoreCase(value)) {
                findings.add(new FileFinding("HIGH", displayPath, "K8S-001",
                        "A container is configured as privileged.",
                        "Disable privileged mode unless strictly required."));
            }
            if ("allowPrivilegeEscalation".equalsIgnoreCase(key)
                    && "true".equalsIgnoreCase(value)) {
                findings.add(new FileFinding("HIGH", displayPath, "K8S-005",
                        "Privilege escalation is allowed.",
                        "Set allowPrivilegeEscalation to false."));
            }
        }

        if (kubernetes && content.contains("kind: deployment")) {
            if (!content.contains("livenessprobe:")) findings.add(new FileFinding(
                    "MEDIUM", displayPath, "K8S-002", "Liveness probe is missing.",
                    "Add a livenessProbe."));
            if (!content.contains("readinessprobe:")) findings.add(new FileFinding(
                    "MEDIUM", displayPath, "K8S-003", "Readiness probe is missing.",
                    "Add a readinessProbe."));
            if (!content.contains("resources:")) findings.add(new FileFinding(
                    "MEDIUM", displayPath, "K8S-004", "Resource requests and limits are missing.",
                    "Configure CPU and memory requests and limits."));
            if (!content.contains("runasnonroot: true")) findings.add(new FileFinding(
                    "MEDIUM", displayPath, "K8S-006", "runAsNonRoot is not explicitly enabled.",
                    "Set securityContext.runAsNonRoot to true."));
        }
        return findings;
    }
}
