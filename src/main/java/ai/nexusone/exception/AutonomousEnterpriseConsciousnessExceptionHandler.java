package ai.nexusone.exception;

import ai.nexusone.controller.AutonomousEnterpriseConsciousnessController;
import java.time.LocalDateTime;
import java.util.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = AutonomousEnterpriseConsciousnessController.class)
public class AutonomousEnterpriseConsciousnessExceptionHandler {
    @ExceptionHandler(AutonomousEnterpriseConsciousnessNotFoundException.class)
    ResponseEntity<Map<String, Object>> notFound(Exception exception) {
        return ResponseEntity.status(404).body(error(404, exception.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class})
    ResponseEntity<Map<String, Object>> badRequest(Exception exception) {
        String message = exception instanceof MethodArgumentNotValidException
                ? "Request validation failed" : exception.getMessage();
        return ResponseEntity.badRequest().body(error(400, message));
    }

    private Map<String, Object> error(int status, String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status);
        body.put("message", message);
        return body;
    }
}
