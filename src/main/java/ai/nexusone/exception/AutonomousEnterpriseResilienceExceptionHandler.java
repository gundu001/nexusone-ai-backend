package ai.nexusone.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestControllerAdvice
public class AutonomousEnterpriseResilienceExceptionHandler {
 @ExceptionHandler({AutonomousEnterpriseResilienceNotFoundException.class,IllegalStateException.class})
 ResponseEntity<Map<String,Object>> handle(RuntimeException e){HttpStatus s=e instanceof AutonomousEnterpriseResilienceNotFoundException?HttpStatus.NOT_FOUND:HttpStatus.BAD_REQUEST;Map<String,Object> body=new LinkedHashMap<>();body.put("timestamp",java.time.LocalDateTime.now());body.put("message",e.getMessage());return ResponseEntity.status(s).body(body);}
 @ExceptionHandler(MethodArgumentNotValidException.class)
 ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException e){Map<String,Object> body=new LinkedHashMap<>();body.put("timestamp",java.time.LocalDateTime.now());body.put("message","Validation failed");Map<String,String> errors=new LinkedHashMap<>();e.getBindingResult().getFieldErrors().forEach(x->errors.put(x.getField(),x.getDefaultMessage()));body.put("errors",errors);return ResponseEntity.badRequest().body(body);}
}
