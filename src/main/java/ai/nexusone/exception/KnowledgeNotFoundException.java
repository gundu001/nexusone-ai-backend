package ai.nexusone.exception;
public class KnowledgeNotFoundException extends RuntimeException{public KnowledgeNotFoundException(Long id){super("Knowledge record not found: "+id);}}
