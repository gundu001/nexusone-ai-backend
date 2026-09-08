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
public class DockerfileAnalyzer {
    public List<FileFinding> analyze(Path file, String displayPath) throws IOException {
        List<FileFinding> findings = new ArrayList<>();
        boolean healthCheck = false;
        boolean nonRootUser = false;

        for (String raw : Files.readAllLines(file)) {
            String line = raw.trim();
            String upper = line.toUpperCase(Locale.ROOT);
            if (line.isBlank() || line.startsWith("#")) continue;

            if (upper.startsWith("FROM ") && line.toLowerCase(Locale.ROOT).contains(":latest")) {
                findings.add(new FileFinding("HIGH", displayPath, "DOCKER-001",
                        "Base image uses the mutable latest tag.",
                        "Pin the image to a fixed version or digest."));
            }
            if (upper.startsWith("USER ")) {
                String user = line.substring(5).trim();
                if ("root".equalsIgnoreCase(user) || "0".equals(user)) {
                    findings.add(new FileFinding("HIGH", displayPath, "DOCKER-002",
                            "Container explicitly runs as root.",
                            "Create and use a non-root user."));
                } else nonRootUser = true;
            }
            if (upper.startsWith("ADD ")) findings.add(new FileFinding(
                    "MEDIUM", displayPath, "DOCKER-003", "ADD instruction is used.",
                    "Prefer COPY unless ADD behavior is required."));
            if (upper.startsWith("HEALTHCHECK ")) healthCheck = true;
        }
        if (!healthCheck) findings.add(new FileFinding(
                "MEDIUM", displayPath, "DOCKER-004", "Dockerfile does not define a HEALTHCHECK.",
                "Add a HEALTHCHECK for the application health endpoint."));
        if (!nonRootUser) findings.add(new FileFinding(
                "MEDIUM", displayPath, "DOCKER-005", "No explicit non-root USER instruction was found.",
                "Add a non-root USER instruction near the end of the Dockerfile."));
        return findings;
    }
}
