package ai.nexusone.exception;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.LocalDateTime; import java.util.*;
@RestControllerAdvice public class StrategicIntelligenceExceptionHandler {
 @ExceptionHandler(StrategicInitiativeNotFoundException.class) ResponseEntity<Map<String,Object>> notFound(Exception e){return ResponseEntity.status(404).body(error(404,e.getMessage()));}
 @ExceptionHandler({IllegalArgumentException.class,MethodArgumentNotValidException.class}) ResponseEntity<Map<String,Object>> bad(Exception e){return ResponseEntity.badRequest().body(error(400,e instanceof MethodArgumentNotValidException?"Request validation failed":e.getMessage()));}
 private Map<String,Object> error(int status,String message){Map<String,Object> m=new LinkedHashMap<>();m.put("timestamp",LocalDateTime.now());m.put("status",status);m.put("message",message);return m;}
}
