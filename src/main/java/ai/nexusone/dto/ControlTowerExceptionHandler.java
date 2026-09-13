package ai.nexusone.dto;

import ai.nexusone.service.InvalidControlActionException;
import ai.nexusone.service.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControlTowerExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ControlTowerDtos.ApiError> notFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return error(HttpStatus.NOT_FOUND, "CONTROL_TOWER_RESOURCE_NOT_FOUND", ex, request);
    }
    @ExceptionHandler(InvalidControlActionException.class)
    ResponseEntity<ControlTowerDtos.ApiError> conflict(InvalidControlActionException ex, HttpServletRequest request) {
        return error(HttpStatus.CONFLICT, "CONTROL_TOWER_ACTION_REJECTED", ex, request);
    }
    private ResponseEntity<ControlTowerDtos.ApiError> error(HttpStatus status, String code, Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(status).body(new ControlTowerDtos.ApiError(code, ex.getMessage(), request.getRequestURI(), LocalDateTime.now()));
    }
}
