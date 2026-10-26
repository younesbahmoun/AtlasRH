package org.example.atlasrh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            fieldErrors.merge(fieldError.getField(), fieldError.getDefaultMessage(), (first, next) -> first + ", " + next);
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
        body.put("message", "Validation failed");
        body.put("errors", fieldErrors);
        body.put("timestamp", Instant.now().toString());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of(
                        "status", 404,
                        "error", "Not Found",
                        "message", exception.getMessage(),
                        "timestamp", Instant.now().toString()
                )
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicate(DuplicateResourceException exception) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        "status", 409,
                        "error", "Conflict",
                        "message", exception.getMessage(),
                        "timestamp", Instant.now().toString()
                )
        );
    }

    @ExceptionHandler(DepartmentHasEmployeesException.class)
    public ResponseEntity<Map<String, Object>> handleDepartmentHasEmployees(DepartmentHasEmployeesException exception) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        "status", 409,
                        "error", "Conflict",
                        "message", exception.getMessage(),
                        "timestamp", Instant.now().toString()
                )
        );
    }

    @ExceptionHandler(InvalidEmployeeManagerException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidEmployeeManager(InvalidEmployeeManagerException exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "status", 400,
                        "error", "Bad Request",
                        "message", exception.getMessage(),
                        "timestamp", Instant.now().toString()
                )
        );
    }

    @ExceptionHandler(InvalidEmployeeDataException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidEmployeeData(
            InvalidEmployeeDataException exception) {

        return ResponseEntity.badRequest().body(
                Map.of(
                        "status", 400,
                        "error", "Bad Request",
                        "message", exception.getMessage(),
                        "timestamp", Instant.now().toString()
                )
        );
    }

//    @ExceptionHandler(InvalidEmployeeManagerException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidEmployeeManager(
//            InvalidEmployeeManagerException exception) {
//
//        ErrorResponse error = new ErrorResponse(
//                exception.getMessage()
//        );
//
//        return ResponseEntity.badRequest().body(error);
//    }

//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException exception) {
//
//        String message = exception.getBindingResult()
//                .getFieldErrors()
//                .get(0)
//                .getDefaultMessage();
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", message));
//    }
}
