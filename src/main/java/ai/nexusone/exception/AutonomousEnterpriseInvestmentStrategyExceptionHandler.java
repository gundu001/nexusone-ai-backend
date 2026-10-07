package ai.nexusone.exception;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime; import java.util.*;
@RestControllerAdvice
public class AutonomousEnterpriseInvestmentStrategyExceptionHandler {
 @ExceptionHandler(AutonomousEnterpriseInvestmentStrategyNotFoundException.class)
 public ResponseEntity<Map<String,Object>> handle(AutonomousEnterpriseInvestmentStrategyNotFoundException e) { return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message",e.getMessage(),"timestamp",LocalDateTime.now())); }
}
