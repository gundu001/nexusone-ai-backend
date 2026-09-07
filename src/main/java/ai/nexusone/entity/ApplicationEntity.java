package ai.nexusone.entity;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
@Entity @Table(name="applications",uniqueConstraints=@UniqueConstraint(columnNames="repositoryUrl"))
public class ApplicationEntity {
 @Id private String id;
 @Column(nullable=false) private String name;
 private String technology;
 @Column(nullable=false,length=600) private String repositoryUrl;
 private String branchName;
 private String status;
 private int healthScore;
 private Instant createdAt;
 public ApplicationEntity(){}
 public ApplicationEntity(String name,String technology,String repositoryUrl,String branchName,String status,int healthScore){this.id=UUID.randomUUID().toString();this.name=name;this.technology=technology;this.repositoryUrl=repositoryUrl;this.branchName=branchName;this.status=status;this.healthScore=healthScore;this.createdAt=Instant.now();}
 public String getId(){return id;} public void setId(String v){id=v;} public String getName(){return name;} public void setName(String v){name=v;} public String getTechnology(){return technology;} public void setTechnology(String v){technology=v;} public String getRepositoryUrl(){return repositoryUrl;} public void setRepositoryUrl(String v){repositoryUrl=v;} public String getBranchName(){return branchName;} public void setBranchName(String v){branchName=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public int getHealthScore(){return healthScore;} public void setHealthScore(int v){healthScore=v;} public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
}
