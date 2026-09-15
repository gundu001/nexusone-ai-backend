package ai.nexusone.dto.response; import java.util.List;
public record RcaResponse(String incidentId,String rootCause,double confidence,String impact,List<String> evidence,List<String> contributingFactors){}
