package ai.nexusone.dto;
public record JenkinsApiBuildResponse(boolean building,String result,Long duration,String url,Integer number) {}
