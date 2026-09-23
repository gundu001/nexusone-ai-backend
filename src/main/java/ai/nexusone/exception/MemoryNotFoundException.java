package ai.nexusone.exception;
public class MemoryNotFoundException extends RuntimeException{public MemoryNotFoundException(Long id){super("Enterprise memory not found: "+id);}}
