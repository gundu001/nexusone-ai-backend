package ai.nexusone.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class AutonomousEnterpriseRevenueIntelligenceExceptionHandler {
    @ExceptionHandler(AutonomousEnterpriseRevenueIntelligenceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(AutonomousEnterpriseRevenueIntelligenceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", exception.getMessage(), "timestamp", LocalDateTime.now()));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors().stream().findFirst().map(e -> e.getField() + ": " + e.getDefaultMessage()).orElse("Validation failed");
        return ResponseEntity.badRequest().body(Map.of("message", message, "timestamp", LocalDateTime.now()));
    }
}
