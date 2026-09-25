package ai.nexusone.exception;
import java.time.LocalDateTime;import java.util.*;
import org.springframework.http.ResponseEntity;import org.springframework.web.bind.MethodArgumentNotValidException;import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class MarketIntelligenceExceptionHandler{
 @ExceptionHandler(MarketIntelligenceNotFoundException.class) ResponseEntity<Map<String,Object>> notFound(Exception e){return ResponseEntity.status(404).body(error(404,e.getMessage()));}
 @ExceptionHandler({IllegalArgumentException.class,MethodArgumentNotValidException.class}) ResponseEntity<Map<String,Object>> bad(Exception e){String m=e instanceof MethodArgumentNotValidException?"Request validation failed":e.getMessage();return ResponseEntity.badRequest().body(error(400,m));}
 private Map<String,Object> error(int s,String m){Map<String,Object> x=new LinkedHashMap<>();x.put("timestamp",LocalDateTime.now());x.put("status",s);x.put("message",m);return x;}
}
