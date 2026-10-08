package ai.nexusone.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;
@RestControllerAdvice
public class AutonomousEnterpriseValuationIntelligenceExceptionHandler {
    @ExceptionHandler(AutonomousEnterpriseValuationIntelligenceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(AutonomousEnterpriseValuationIntelligenceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage(), "timestamp", LocalDateTime.now()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream().findFirst().map(e -> e.getField()+": "+e.getDefaultMessage()).orElse("Validation failed");
        return ResponseEntity.badRequest().body(Map.of("message", message, "timestamp", LocalDateTime.now()));
    }
}
