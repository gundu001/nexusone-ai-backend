package ai.nexusone.exception;

import ai.nexusone.controller.ChiefAiOfficerIntelligenceController;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = ChiefAiOfficerIntelligenceController.class)
public class ChiefAiOfficerIntelligenceExceptionHandler {

    @ExceptionHandler(ChiefAiOfficerIntelligenceNotFoundException.class)
    ResponseEntity<Map<String, Object>> notFound(ChiefAiOfficerIntelligenceNotFoundException exception) {
        return ResponseEntity.status(404).body(error(404, exception.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    ResponseEntity<Map<String, Object>> badRequest(Exception exception) {
        String message = exception instanceof MethodArgumentNotValidException
                ? "Request validation failed"
                : exception.getMessage();
        return ResponseEntity.badRequest().body(error(400, message));
    }

    private Map<String, Object> error(int status, String message) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status);
        response.put("message", message);
        return response;
    }
}
