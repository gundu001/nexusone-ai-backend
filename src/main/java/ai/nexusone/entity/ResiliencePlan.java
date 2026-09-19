package ai.nexusone.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resilience_plans")
public class ResiliencePlan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String applicationName;
    @Column(nullable = false) private String environment;
    @Column(nullable = false) private String primaryProvider;
    @Column(nullable = false) private String primaryRegion;
    @Column(nullable = false) private String secondaryProvider;
    @Column(nullable = false) private String secondaryRegion;
    private int rtoMinutes;
    private int rpoMinutes;
    private double readinessScore;
    @Column(nullable = false) private String status;
    private LocalDateTime createdAt;

    @PrePersist void prePersist() { if (createdAt == null) createdAt = LocalDateTime.now(); }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getApplicationName(){return applicationName;} public void setApplicationName(String v){applicationName=v;}
    public String getEnvironment(){return environment;} public void setEnvironment(String v){environment=v;}
    public String getPrimaryProvider(){return primaryProvider;} public void setPrimaryProvider(String v){primaryProvider=v;}
    public String getPrimaryRegion(){return primaryRegion;} public void setPrimaryRegion(String v){primaryRegion=v;}
    public String getSecondaryProvider(){return secondaryProvider;} public void setSecondaryProvider(String v){secondaryProvider=v;}
    public String getSecondaryRegion(){return secondaryRegion;} public void setSecondaryRegion(String v){secondaryRegion=v;}
    public int getRtoMinutes(){return rtoMinutes;} public void setRtoMinutes(int v){rtoMinutes=v;}
    public int getRpoMinutes(){return rpoMinutes;} public void setRpoMinutes(int v){rpoMinutes=v;}
    public double getReadinessScore(){return readinessScore;} public void setReadinessScore(double v){readinessScore=v;}
    public String getStatus(){return status;} public void setStatus(String v){status=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
