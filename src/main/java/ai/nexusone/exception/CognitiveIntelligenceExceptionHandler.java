package ai.nexusone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class CognitiveIntelligenceExceptionHandler {

    @ExceptionHandler(CognitiveInsightNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            CognitiveInsightNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error(HttpStatus.NOT_FOUND, exception.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessError(
            IllegalArgumentException exception) {

        return ResponseEntity.badRequest()
                .body(error(HttpStatus.BAD_REQUEST, exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException exception) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> response = error(
                HttpStatus.BAD_REQUEST,
                "Request validation failed"
        );
        response.put("errors", fieldErrors);

        return ResponseEntity.badRequest().body(response);
    }

    private Map<String, Object> error(HttpStatus status, String message) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());
        response.put("message", message);
        return response;
    }
}
