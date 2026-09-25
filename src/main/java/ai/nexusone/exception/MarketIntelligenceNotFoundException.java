package ai.nexusone.exception;
public class MarketIntelligenceNotFoundException extends RuntimeException{
 public MarketIntelligenceNotFoundException(Long id){super("Market intelligence report not found: "+id);}
}
