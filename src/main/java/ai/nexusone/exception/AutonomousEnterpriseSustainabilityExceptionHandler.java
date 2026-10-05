package ai.nexusone.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestControllerAdvice
public class AutonomousEnterpriseSustainabilityExceptionHandler {
 @ExceptionHandler({AutonomousEnterpriseSustainabilityNotFoundException.class,IllegalStateException.class})
 ResponseEntity<Map<String,Object>> handle(RuntimeException e){HttpStatus s=e instanceof AutonomousEnterpriseSustainabilityNotFoundException?HttpStatus.NOT_FOUND:HttpStatus.BAD_REQUEST;Map<String,Object> body=new LinkedHashMap<>();body.put("timestamp",java.time.LocalDateTime.now());body.put("message",e.getMessage());return ResponseEntity.status(s).body(body);}
}
