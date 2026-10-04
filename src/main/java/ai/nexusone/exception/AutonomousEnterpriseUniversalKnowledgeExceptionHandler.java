package ai.nexusone.exception;
import org.springframework.http.*;import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.*;import java.time.LocalDateTime;import java.util.*;
@RestControllerAdvice
public class AutonomousEnterpriseUniversalKnowledgeExceptionHandler {
 @ExceptionHandler(AutonomousEnterpriseUniversalKnowledgeNotFoundException.class) ResponseEntity<Map<String,Object>> notFound(RuntimeException e){return body(HttpStatus.NOT_FOUND,e.getMessage());}
 @ExceptionHandler(IllegalStateException.class) ResponseEntity<Map<String,Object>> badState(RuntimeException e){return body(HttpStatus.BAD_REQUEST,e.getMessage());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e){String m=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Validation failed");return body(HttpStatus.BAD_REQUEST,m);}
 private ResponseEntity<Map<String,Object>> body(HttpStatus s,String m){return ResponseEntity.status(s).body(Map.of("timestamp",LocalDateTime.now(),"status",s.value(),"message",m));}
}
