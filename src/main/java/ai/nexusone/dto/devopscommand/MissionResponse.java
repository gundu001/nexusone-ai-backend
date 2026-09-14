package ai.nexusone.dto.devopscommand;

public class MissionResponse {
    private final Long missionId;
    private final String name;
    private final String description;
    private final String status;
    private final int healthScore;
    private final String sourcePhase;

    public MissionResponse(Long missionId, String name, String description, String status, int healthScore, String sourcePhase) {
        this.missionId = missionId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.healthScore = healthScore;
        this.sourcePhase = sourcePhase;
    }

    public Long getMissionId() { return missionId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public int getHealthScore() { return healthScore; }
    public String getSourcePhase() { return sourcePhase; }
}
