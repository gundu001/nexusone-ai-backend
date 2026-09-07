package ai.nexusone.exception;
import org.springframework.http.*; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import org.springframework.web.client.HttpStatusCodeException; import java.time.Instant; import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(NotFoundException.class) ResponseEntity<Map<String,Object>> nf(NotFoundException e){return body(HttpStatus.NOT_FOUND,e.getMessage());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,Object>> val(MethodArgumentNotValidException e){String m=e.getBindingResult().getFieldErrors().stream().map(x->x.getField()+" "+x.getDefaultMessage()).toList().toString();return body(HttpStatus.BAD_REQUEST,m);}
 @ExceptionHandler(HttpStatusCodeException.class) ResponseEntity<Map<String,Object>> remote(HttpStatusCodeException e){return body(HttpStatus.BAD_GATEWAY,"GitHub API returned "+e.getStatusCode()+". Check repository URL, token and permissions.");}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,Object>> bad(IllegalArgumentException e){return body(HttpStatus.BAD_REQUEST,e.getMessage());}
 @ExceptionHandler(Exception.class) ResponseEntity<Map<String,Object>> generic(Exception e){return body(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());}
 private ResponseEntity<Map<String,Object>> body(HttpStatus s,String m){Map<String,Object>b=new LinkedHashMap<>();b.put("timestamp",Instant.now());b.put("status",s.value());b.put("error",s.getReasonPhrase());b.put("message",m);return ResponseEntity.status(s).body(b);}
}
