package ai.nexusone.exception;
public class ChairmanIntelligenceNotFoundException extends RuntimeException { public ChairmanIntelligenceNotFoundException(Long id){super("Chairman intelligence report not found: "+id);} }
