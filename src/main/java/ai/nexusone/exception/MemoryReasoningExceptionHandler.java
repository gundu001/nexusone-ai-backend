package ai.nexusone.exception;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.util.*;
@RestControllerAdvice public class MemoryReasoningExceptionHandler{
 @ExceptionHandler(MemoryNotFoundException.class) ResponseEntity<Map<String,Object>> notFound(MemoryNotFoundException e){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("timestamp",LocalDateTime.now(),"message",e.getMessage()));}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,Object>> business(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("timestamp",LocalDateTime.now(),"message",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e){var errors=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+": "+x.getDefaultMessage()).toList();return ResponseEntity.badRequest().body(Map.of("timestamp",LocalDateTime.now(),"message","Validation failed","errors",errors));}
}
