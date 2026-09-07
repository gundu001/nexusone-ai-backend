package ai.nexusone.service;

import ai.nexusone.dto.RepositoryScanResult;
import ai.nexusone.entity.RepositoryScanResultEntity;
import ai.nexusone.repository.RepositoryScanResultRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.stream.Stream;

@Service
public class RepositoryScannerService {

    private final RepositoryScanResultRepository scanResultRepository;

    @Value("${nexusone.repositories.path:C:/NexusOne-Sample-Applications}")
    private String repositoryBasePath;

    public RepositoryScannerService(
            RepositoryScanResultRepository scanResultRepository) {

        this.scanResultRepository = scanResultRepository;
    }

    public RepositoryScanResult scanRepository(String repoName)
            throws IOException {

        Path repositoriesPath =
                Paths.get(repositoryBasePath)
                        .toAbsolutePath()
                        .normalize();

        Path repositoryPath =
                repositoriesPath.resolve(repoName)
                        .normalize();

        RepositoryScanResult result =
                new RepositoryScanResult();

        result.setRepositoryName(repoName);
        result.setRepositoryPath(repositoryPath.toString());

        try (Stream<Path> paths = Files.walk(repositoryPath)) {

            paths.filter(Files::isRegularFile)
                    .forEach(path -> {

                        String fileName =
                                path.getFileName()
                                        .toString()
                                        .toLowerCase(Locale.ROOT);

                        if ("pom.xml".equals(fileName)) {
                            result.setPomFilePresent(true);
                        }

                        if ("dockerfile".equals(fileName)) {
                            result.setDockerfilePresent(true);
                        }

                        if ("readme.md".equals(fileName)) {
                            result.setReadmePresent(true);
                        }

                        if ("application.properties".equals(fileName)
                                || "application.yml".equals(fileName)
                                || "application.yaml".equals(fileName)) {

                            result.setApplicationConfigPresent(true);
                        }

                        if (fileName.endsWith(".java")) {

                            result.setJavaFileCount(
                                    result.getJavaFileCount() + 1
                            );
                        }
                    });
        }

        if (result.isPomFilePresent()) {

            result.setDetectedTechnology("JAVA_MAVEN");

        } else {

            result.setDetectedTechnology("UNKNOWN");
        }

        if (!result.isKubernetesFilesPresent()) {

            result.getWarnings().add(
                    "Kubernetes deployment files were not found."
            );
        }

        saveScanResult(result);

        return result;
    }

    private void saveScanResult(
            RepositoryScanResult result) {

        RepositoryScanResultEntity entity =
                new RepositoryScanResultEntity();

        entity.setRepositoryName(
                result.getRepositoryName());

        entity.setTechnology(
                result.getDetectedTechnology());

        entity.setJavaFileCount(
                result.getJavaFileCount());

        entity.setPomFilePresent(
                result.isPomFilePresent());

        entity.setDockerfilePresent(
                result.isDockerfilePresent());

        entity.setApplicationConfigPresent(
                result.isApplicationConfigPresent());

        entity.setReadmePresent(
                result.isReadmePresent());

        entity.setScanStatus("SUCCESS");

        entity.setScanTime(
                LocalDateTime.now());

        scanResultRepository.save(entity);
    }
}