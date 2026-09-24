package ai.nexusone.exception;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class BoardroomIntelligenceExceptionHandler {
 @ExceptionHandler(BoardroomBriefingNotFoundException.class)
 ResponseEntity<Map<String,Object>> notFound(Exception e){return ResponseEntity.status(404).body(error(404,e.getMessage()));}
 @ExceptionHandler({IllegalArgumentException.class,MethodArgumentNotValidException.class})
 ResponseEntity<Map<String,Object>> badRequest(Exception e){String m=e instanceof MethodArgumentNotValidException?"Request validation failed":e.getMessage();return ResponseEntity.badRequest().body(error(400,m));}
 private Map<String,Object> error(int s,String m){Map<String,Object> r=new LinkedHashMap<>();r.put("timestamp",LocalDateTime.now());r.put("status",s);r.put("message",m);return r;}
}
