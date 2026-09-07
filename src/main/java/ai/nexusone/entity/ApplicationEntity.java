package ai.nexusone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "applications")
public class ApplicationEntity {

 @Id
 private String id;

 @Column(nullable = false)
 private String name;

 private String technology;

 private String repositoryUrl;

 private String status;

 // Default Constructor
 public ApplicationEntity() {
 }

 // Parameterized Constructor
 public ApplicationEntity(
         String name,
         String technology,
         String repositoryUrl,
         String status) {

  this.id = UUID.randomUUID().toString();
  this.name = name;
  this.technology = technology;
  this.repositoryUrl = repositoryUrl;
  this.status = status;
 }

 // Getters and Setters

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public String getTechnology() {
  return technology;
 }

 public void setTechnology(String technology) {
  this.technology = technology;
 }

 public String getRepositoryUrl() {
  return repositoryUrl;
 }

 public void setRepositoryUrl(String repositoryUrl) {
  this.repositoryUrl = repositoryUrl;
 }

 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }
}