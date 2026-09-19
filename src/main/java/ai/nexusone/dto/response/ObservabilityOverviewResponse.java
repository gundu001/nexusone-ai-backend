package ai.nexusone.dto.response;
public record ObservabilityOverviewResponse(long monitoredServices,long healthyServices,long degradedServices,long criticalAlerts,long analysesSaved) {}
