package ai.nexusone.service;

import ai.nexusone.dto.response.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChangeRiskPredictionService {
    public ChangeRiskOverviewResponse getOverview() {
        return new ChangeRiskOverviewResponse(125,6,34,85,47.0,12,94.5);
    }

    public List<ChangeRiskAssessmentResponse> getAssessments() {
        return List.of(
            new ChangeRiskAssessmentResponse(5301L,"NexusOne Backend","FEATURE",18,1240,2,82.0,"HIGH","REQUIRES_APPROVAL"),
            new ChangeRiskAssessmentResponse(5302L,"Risk Engine","MODEL_UPDATE",9,680,1,74.0,"HIGH","REQUIRES_APPROVAL"),
            new ChangeRiskAssessmentResponse(5303L,"Release Orchestrator","CONFIGURATION",6,145,0,46.0,"MEDIUM","REVIEW_RECOMMENDED"),
            new ChangeRiskAssessmentResponse(5304L,"Executive Dashboard","BUG_FIX",4,92,0,24.0,"LOW","READY"),
            new ChangeRiskAssessmentResponse(5305L,"Jenkins Integration","DEPENDENCY",11,410,1,58.0,"MEDIUM","REVIEW_RECOMMENDED")
        );
    }

    public List<ImpactAnalysisResponse> getImpactAnalysis() {
        return List.of(
            new ImpactAnalysisResponse(5311L,5301L,"NexusOne Backend",7,2400,12,"HIGH","HIGH"),
            new ImpactAnalysisResponse(5312L,5302L,"Risk Engine",4,1200,8,"HIGH","MEDIUM"),
            new ImpactAnalysisResponse(5313L,5303L,"Release Orchestrator",3,650,4,"MEDIUM","LOW"),
            new ImpactAnalysisResponse(5314L,5304L,"Executive Dashboard",1,180,0,"LOW","LOW"),
            new ImpactAnalysisResponse(5315L,5305L,"Jenkins Integration",5,900,6,"MEDIUM","MEDIUM")
        );
    }

    public ChangeRiskTrendResponse getTrends() {
        return new ChangeRiskTrendResponse(47.0,53.0,11.32,List.of(
            new ChangeRiskTrendResponse.TrendPoint("Mon",55.0,3,18,2),
            new ChangeRiskTrendResponse.TrendPoint("Tue",52.0,2,21,1),
            new ChangeRiskTrendResponse.TrendPoint("Wed",49.0,2,20,1),
            new ChangeRiskTrendResponse.TrendPoint("Thu",46.0,1,24,1),
            new ChangeRiskTrendResponse.TrendPoint("Fri",47.0,2,26,1)
        ));
    }

    public List<ChangeRiskRecommendationResponse> getRecommendations() {
        return List.of(
            new ChangeRiskRecommendationResponse(5321L,5301L,"CRITICAL","NexusOne Backend","BLAST_RADIUS","Large code change affects multiple services.","Require architecture approval, integration testing, canary rollout, and verified rollback."),
            new ChangeRiskRecommendationResponse(5322L,5302L,"HIGH","Risk Engine","MODEL_SAFETY","Model update has elevated historical failure correlation.","Run regression validation against the approved baseline before promotion."),
            new ChangeRiskRecommendationResponse(5323L,5305L,"MEDIUM","Jenkins Integration","DEPENDENCY","Dependency changes may affect pipeline compatibility.","Validate plugins and execute a staging pipeline before production rollout."),
            new ChangeRiskRecommendationResponse(5324L,5304L,"LOW","Executive Dashboard","DELIVERY","Change has limited scope and low predicted impact.","Proceed with standard automated tests and monitored rolling deployment.")
        );
    }
}
