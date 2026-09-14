package ai.nexusone.dto.response;

public record KubernetesPodResponse(
        String podName,
        String namespace,
        String nodeName,
        String status,
        int restartCount,
        double cpuUsage,
        double memoryUsage,
        String riskLevel) {
}
