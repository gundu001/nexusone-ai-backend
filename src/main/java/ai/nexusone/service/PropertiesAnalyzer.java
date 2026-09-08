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
public class PropertiesAnalyzer {
    private final SecretDetectionService secretDetectionService;

    public PropertiesAnalyzer(SecretDetectionService secretDetectionService) {
        this.secretDetectionService = secretDetectionService;
    }

    public List<FileFinding> analyze(Path file, String displayPath) throws IOException {
        List<FileFinding> findings = new ArrayList<>();
        for (String raw : Files.readAllLines(file)) {
            String line = raw.trim();
            if (line.isBlank() || line.startsWith("#") || line.startsWith("!")) continue;
            int separator = findSeparator(line);
            if (separator < 0) continue;

            String key = line.substring(0, separator).trim();
            String value = line.substring(separator + 1).trim();
            String lowerKey = key.toLowerCase(Locale.ROOT);
            findings.addAll(secretDetectionService.inspect(key, value, displayPath));

            if ("management.endpoints.web.exposure.include".equals(lowerKey) && "*".equals(value)) {
                findings.add(new FileFinding("HIGH", displayPath, "CONFIG-001",
                        "All Spring Boot Actuator endpoints are exposed.",
                        "Expose only required endpoints and secure access."));
            }
            if ("management.endpoint.env.show-values".equals(lowerKey)
                    && "always".equalsIgnoreCase(value)) {
                findings.add(new FileFinding("HIGH", displayPath, "CONFIG-002",
                        "Actuator environment endpoint is configured to always show values.",
                        "Set show-values to never or when-authorized."));
            }
            if ("spring.jpa.show-sql".equals(lowerKey) && "true".equalsIgnoreCase(value)) {
                findings.add(new FileFinding("LOW", displayPath, "CONFIG-003",
                        "SQL statement logging is enabled.",
                        "Disable SQL logging in production."));
            }
            if (("server.error.include-stacktrace".equals(lowerKey)
                    || "server.error.include-message".equals(lowerKey))
                    && ("always".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value))) {
                findings.add(new FileFinding("MEDIUM", displayPath, "CONFIG-004",
                        "Detailed server error information may be exposed.",
                        "Disable detailed error output in production."));
            }
            if (lowerKey.contains("ssl.enabled") && "false".equalsIgnoreCase(value)) {
                findings.add(new FileFinding("MEDIUM", displayPath, "CONFIG-005",
                        "SSL is explicitly disabled.",
                        "Enable TLS for production traffic or terminate TLS at a trusted gateway."));
            }
        }
        return findings;
    }

    private int findSeparator(String line) {
        int equals = line.indexOf('=');
        int colon = line.indexOf(':');
        if (equals < 0) return colon;
        if (colon < 0) return equals;
        return Math.min(equals, colon);
    }
}
