package ai.nexusone.dto;

import java.util.List;

public record RiskResponse(int riskScore,String level,List<String> recommendations) {}
