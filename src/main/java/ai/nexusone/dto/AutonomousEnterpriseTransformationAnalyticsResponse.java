package ai.nexusone.dto;
public record AutonomousEnterpriseTransformationAnalyticsResponse(
 long total,long generated,long reviewed,long approved,long rejected,long published,
 double averageVision,double averageRoadmap,double averageOperatingModel,
 double averageWorkforce,double averageProcess,double averageTechnology,
 double averageCustomer,double averageExecution,double enterpriseTransformationScore) {}
