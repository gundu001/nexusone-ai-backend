package ai.nexusone.dto.response.eoc;
public record EocHealthResponse(int overall, int deploymentReadiness, int reliability, int observability, int autonomousOperations) {}
