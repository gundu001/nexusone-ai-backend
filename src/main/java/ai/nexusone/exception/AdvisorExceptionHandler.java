package ai.nexusone.exception;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
@RestControllerAdvice
public class AdvisorExceptionHandler {
    @ExceptionHandler(RiskAnalysisNotFoundException.class)
    public ResponseEntity<Map<String,Object>> notFound(RiskAnalysisNotFoundException ex){return response(HttpStatus.NOT_FOUND,ex.getMessage());}
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> badRequest(IllegalArgumentException ex){return response(HttpStatus.BAD_REQUEST,ex.getMessage());}
    private ResponseEntity<Map<String,Object>> response(HttpStatus status,String message){
        Map<String,Object> body=new LinkedHashMap<>(); body.put("timestamp",LocalDateTime.now());
        body.put("status",status.value()); body.put("error",status.getReasonPhrase()); body.put("message",message);
        return ResponseEntity.status(status).body(body);
    }
}
