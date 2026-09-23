package ai.nexusone.exception;
import org.springframework.http.ResponseEntity; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.util.*;
@RestControllerAdvice
public class LearningOptimizationExceptionHandler {
 @ExceptionHandler({LearningNotFoundException.class,IllegalArgumentException.class}) ResponseEntity<Map<String,Object>> business(RuntimeException ex){return ResponseEntity.badRequest().body(Map.of("timestamp",LocalDateTime.now(),"message",ex.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException ex){var errors=ex.getBindingResult().getFieldErrors().stream().map(e->e.getField()+": "+e.getDefaultMessage()).toList();return ResponseEntity.badRequest().body(Map.of("timestamp",LocalDateTime.now(),"message","Validation failed","errors",errors));}
}
