package ai.nexusone.dto.response;
public record ReleaseCopilotOverviewResponse(int totalReleases,int readyReleases,int conditionalReleases,int blockedReleases,double averageReadinessScore) {}
