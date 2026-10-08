package ai.nexusone.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class AutonomousEnterpriseProfitabilityIntelligenceExceptionHandler {
    @ExceptionHandler(AutonomousEnterpriseProfitabilityIntelligenceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(AutonomousEnterpriseProfitabilityIntelligenceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", exception.getMessage(), "timestamp", LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .findFirst().map(error -> error.getField() + ": " + error.getDefaultMessage()).orElse("Validation failed");
        return ResponseEntity.badRequest().body(Map.of("message", message, "timestamp", LocalDateTime.now()));
    }
}
