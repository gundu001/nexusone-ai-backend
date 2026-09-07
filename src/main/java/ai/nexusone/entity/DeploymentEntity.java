package ai.nexusone.entity;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="deployments")
public class DeploymentEntity {
 @Id private String id; private String applicationName; private String environment; private String provider; private String version; private String status; private int riskScore; private Instant createdAt;
 public DeploymentEntity(){}
 public DeploymentEntity(String a,String e,String p,String v,String s,int r){id=UUID.randomUUID().toString();applicationName=a;environment=e;provider=p;version=v;status=s;riskScore=r;createdAt=Instant.now();}
 public String getId(){return id;} public void setId(String v){id=v;} public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;} public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;} public String getProvider(){return provider;} public void setProvider(String v){provider=v;} public String getVersion(){return version;} public void setVersion(String v){version=v;} public String getStatus(){return status;} public void setStatus(String v){status=v;} public int getRiskScore(){return riskScore;} public void setRiskScore(int v){riskScore=v;} public Instant getCreatedAt(){return createdAt;} public void setCreatedAt(Instant v){createdAt=v;}
}
