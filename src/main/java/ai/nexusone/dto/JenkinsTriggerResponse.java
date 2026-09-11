package ai.nexusone.dto;
public record JenkinsTriggerResponse(Long executionId,Long queueId,Integer buildNumber,String status,String jenkinsUrl,String message) {}
