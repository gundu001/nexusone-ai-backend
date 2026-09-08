package ai.nexusone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

 @ExceptionHandler(NotFoundException.class)
 public ResponseEntity<Map<String, Object>> handleNotFound(
         NotFoundException ex) {

  return buildResponse(
          HttpStatus.NOT_FOUND,
          ex.getMessage()
  );
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 public ResponseEntity<Map<String, Object>> handleValidation(
         MethodArgumentNotValidException ex) {

  String message = ex.getBindingResult()
          .getFieldErrors()
          .stream()
          .map(error ->
                  error.getField()
                          + " "
                          + error.getDefaultMessage())
          .toList()
          .toString();

  return buildResponse(
          HttpStatus.BAD_REQUEST,
          message
  );
 }

 @ExceptionHandler(HttpStatusCodeException.class)
 public ResponseEntity<Map<String, Object>>
 handleGitHubException(
         HttpStatusCodeException ex) {

  return buildResponse(
          HttpStatus.BAD_GATEWAY,
          "GitHub API returned "
                  + ex.getStatusCode()
                  + ". Check repository URL, token and permissions."
  );
 }

 @ExceptionHandler(IllegalArgumentException.class)
 public ResponseEntity<Map<String, Object>>
 handleBadRequest(
         IllegalArgumentException ex) {

  return buildResponse(
          HttpStatus.BAD_REQUEST,
          ex.getMessage()
  );
 }

 @ExceptionHandler(Exception.class)
 public ResponseEntity<Map<String, Object>>
 handleGeneric(
         Exception ex) {

  return buildResponse(
          HttpStatus.INTERNAL_SERVER_ERROR,
          ex.getMessage()
  );
 }

 private ResponseEntity<Map<String, Object>>
 buildResponse(
         HttpStatus status,
         String message) {

  Map<String, Object> body =
          new LinkedHashMap<>();

  body.put(
          "timestamp",
          Instant.now()
  );

  body.put(
          "status",
          status.value()
  );

  body.put(
          "error",
          status.getReasonPhrase()
  );

  body.put(
          "message",
          message
  );

  return ResponseEntity
          .status(status)
          .body(body);
 }
}