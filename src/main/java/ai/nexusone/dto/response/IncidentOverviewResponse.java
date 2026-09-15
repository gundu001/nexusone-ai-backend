package ai.nexusone.dto.response;

public record IncidentOverviewResponse(long total, long critical, long open, long resolved){}
