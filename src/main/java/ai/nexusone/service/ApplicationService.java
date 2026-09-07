package ai.nexusone.service;

import ai.nexusone.dto.AnalyzeRequest;
import ai.nexusone.entity.ApplicationEntity;
import ai.nexusone.repository.ApplicationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

 private final ApplicationRepository repository;

 public ApplicationService(ApplicationRepository repository) {
  this.repository = repository;
 }

 /**
  * Get all applications
  */
 public List<ApplicationEntity> findAll() {
  return repository.findAll();
 }

 /**
  * Analyze repository and create application entry
  */
 public ApplicationEntity analyze(AnalyzeRequest request) {

  String repositoryUrl = request.repositoryUrl();

  String technology;

  if (repositoryUrl.toLowerCase().contains("node")) {
   technology = "React / Node.js";
  } else if (repositoryUrl.toLowerCase().contains("python")) {
   technology = "Python";
  } else {
   technology = "Java 21 / Spring Boot";
  }

  String applicationName =
          repositoryUrl.substring(
                          repositoryUrl.lastIndexOf("/") + 1)
                  .replace(".git", "");

  ApplicationEntity application = new ApplicationEntity(
          applicationName,
          technology,
          repositoryUrl,
          "HEALTHY"
  );

  return repository.save(application);
 }
}