package ai.nexusone.exception;
public class LearningNotFoundException extends RuntimeException { public LearningNotFoundException(Long id){super("Learning record not found: "+id);} }
