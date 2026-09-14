package ai.nexusone.dto.response;

public record KubernetesDeploymentResponse(
        String deploymentName,
        String namespace,
        int desiredReplicas,
        int availableReplicas,
        int unavailableReplicas,
        String image,
        String status,
        double availabilityPercentage) {
}
