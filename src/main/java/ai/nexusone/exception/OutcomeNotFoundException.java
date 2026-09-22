package ai.nexusone.exception;

public class OutcomeNotFoundException extends RuntimeException { public OutcomeNotFoundException(Long id){ super("Outcome not found: " + id); } }
