package br.com.talkspace.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleNotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleUnauthorizedError(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity handleAuthenticationError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication fail");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedError() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleBadInternalError(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + exception.getLocalizedMessage());
    }

}
