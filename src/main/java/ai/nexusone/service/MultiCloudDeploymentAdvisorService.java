package ai.nexusone.service;

import ai.nexusone.dto.response.CloudProviderResponse;
import ai.nexusone.dto.response.CostOptimizationResponse;
import ai.nexusone.dto.response.DeploymentStrategyResponse;
import ai.nexusone.dto.response.MultiCloudOverviewResponse;
import ai.nexusone.dto.response.MultiCloudRecommendationResponse;
import ai.nexusone.dto.response.RiskAssessmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultiCloudDeploymentAdvisorService {

    public MultiCloudOverviewResponse getOverview() {
        return new MultiCloudOverviewResponse(4, 9, 68, 93.0, 15420.0, 3380.0, "AZURE");
    }

    public List<CloudProviderResponse> getProviders() {
        return List.of(
                new CloudProviderResponse("AZURE", "Central India", 3, 22, 96.0, 99.95, 4800.0, "HEALTHY"),
                new CloudProviderResponse("AWS", "ap-south-1", 3, 20, 94.0, 99.93, 5100.0, "HEALTHY"),
                new CloudProviderResponse("GCP", "asia-south1", 2, 16, 91.0, 99.90, 3520.0, "WARNING"),
                new CloudProviderResponse("OPENSHIFT", "On-Premises", 1, 10, 89.0, 99.85, 2000.0, "WARNING")
        );
    }

    public List<CostOptimizationResponse> getCostOptimization() {
        return List.of(
                new CostOptimizationResponse(5201, "AZURE", 4800.0, 1080.0, 22.5, "Apply reserved capacity to stable production workloads.", "HIGH"),
                new CostOptimizationResponse(5202, "AWS", 5100.0, 1250.0, 24.51, "Rightsize underutilized compute and use Savings Plans.", "HIGH"),
                new CostOptimizationResponse(5203, "GCP", 3520.0, 700.0, 19.89, "Move predictable workloads to committed-use discounts.", "MEDIUM"),
                new CostOptimizationResponse(5204, "OPENSHIFT", 2000.0, 350.0, 17.5, "Consolidate low-utilization worker nodes.", "MEDIUM")
        );
    }

    public List<DeploymentStrategyResponse> getDeploymentStrategies() {

        return List.of(

                new DeploymentStrategyResponse(
                        5211L,
                        "NexusOne Backend",
                        "PRODUCTION",
                        "AZURE",
                        "Central India",
                        "BLUE_GREEN",
                        96.0,
                        "Strong health, regional proximity, and controlled cutover support."
                ),

                new DeploymentStrategyResponse(
                        5212L,
                        "Risk Engine",
                        "PRODUCTION",
                        "AWS",
                        "ap-south-1",
                        "CANARY",
                        92.0,
                        "Gradual exposure reduces model-service release risk."
                ),

                new DeploymentStrategyResponse(
                        5213L,
                        "Analytics Workload",
                        "STAGING",
                        "GCP",
                        "asia-south1",
                        "ROLLING",
                        88.0,
                        "Suitable cost profile and capacity for analytical processing."
                ),

                new DeploymentStrategyResponse(
                        5214L,
                        "Compliance Service",
                        "PRODUCTION",
                        "OPENSHIFT",
                        "On-Premises",
                        "RECREATE",
                        84.0,
                        "On-premises placement supports the defined hosting constraint."
                )
        );
    }

    public List<RiskAssessmentResponse> getRiskAssessment() {
        return List.of(
                new RiskAssessmentResponse(5221, "GCP", "analytics-prod", "MEDIUM", "Workload is concentrated in one region.", "Add regional failover and validate backup restoration."),
                new RiskAssessmentResponse(5222, "OPENSHIFT", "worker-pool-01", "HIGH", "Worker capacity has limited failure headroom.", "Add worker capacity and configure workload anti-affinity."),
                new RiskAssessmentResponse(5223, "AWS", "risk-engine", "MEDIUM", "Peak utilization approaches the scaling threshold.", "Configure predictive scaling for peak business hours."),
                new RiskAssessmentResponse(5224, "AZURE", "nexusone-backend", "LOW", "No material deployment risk is currently detected.", "Continue monitoring availability and spend trends.")
        );
    }

    public List<MultiCloudRecommendationResponse> getRecommendations() {
        return List.of(
                new MultiCloudRecommendationResponse(5231, "HIGH", "OPENSHIFT", "RELIABILITY", "Increase worker-pool redundancy before the next production release.", "Add capacity and verify pod distribution across workers."),
                new MultiCloudRecommendationResponse(5232, "HIGH", "AWS", "COST", "Apply a commitment model to predictable production compute.", "Review Savings Plans for stable baseline usage."),
                new MultiCloudRecommendationResponse(5233, "MEDIUM", "GCP", "RESILIENCE", "Reduce dependency on a single deployment region.", "Create a tested secondary-region recovery plan."),
                new MultiCloudRecommendationResponse(5234, "LOW", "AZURE", "STRATEGY", "Use blue-green deployment for the NexusOne backend release.", "Provision the green environment and validate readiness before traffic switch.")
        );
    }
}
