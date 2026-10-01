package ai.nexusone.exception;
public class FuturePredictionIntelligenceNotFoundException extends RuntimeException{
 public FuturePredictionIntelligenceNotFoundException(Long id){super("Future prediction intelligence report not found: "+id);}
}
