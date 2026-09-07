package ai.nexusone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "deployments")
public class DeploymentEntity {

 @Id
 private String id;

 private String applicationName;

 private String environment;

 private String provider;

 private String version;

 private String status;

 private int riskScore;

 private Instant createdAt;

 // Default Constructor
 public DeploymentEntity() {
 }

 // Parameterized Constructor
 public DeploymentEntity(
         String applicationName,
         String environment,
         String provider,
         String version,
         String status,
         int riskScore) {

  this.id = UUID.randomUUID().toString();
  this.applicationName = applicationName;
  this.environment = environment;
  this.provider = provider;
  this.version = version;
  this.status = status;
  this.riskScore = riskScore;
  this.createdAt = Instant.now();
 }

 // Getters and Setters

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getApplicationName() {
  return applicationName;
 }

 public void setApplicationName(String applicationName) {
  this.applicationName = applicationName;
 }

 public String getEnvironment() {
  return environment;
 }

 public void setEnvironment(String environment) {
  this.environment = environment;
 }

 public String getProvider() {
  return provider;
 }

 public void setProvider(String provider) {
  this.provider = provider;
 }

 public String getVersion() {
  return version;
 }

 public void setVersion(String version) {
  this.version = version;
 }

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public int getRiskScore() {
  return riskScore;
 }

 public void setRiskScore(int riskScore) {
  this.riskScore = riskScore;
 }

 public Instant getCreatedAt() {
  return createdAt;
 }

 public void setCreatedAt(Instant createdAt) {
  this.createdAt = createdAt;
 }
}