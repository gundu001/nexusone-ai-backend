package ai.nexusone.exception;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant; import java.util.*;

@RestControllerAdvice public class ApiExceptionHandler {record ErrorResponse(Instant timestamp,int status,String message){}
 @ExceptionHandler(NoSuchElementException.class) ResponseEntity<ErrorResponse> missing(NoSuchElementException e){return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(Instant.now(),404,e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErrorResponse> invalid(MethodArgumentNotValidException e){return ResponseEntity.badRequest().body(new ErrorResponse(Instant.now(),400,"incidentId and question are required"));}}
