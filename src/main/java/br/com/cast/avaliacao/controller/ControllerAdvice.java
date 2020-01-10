package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.exception.CategoryInUseException;
import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class ControllerAdvice {

    private ResponseEntity<?> exceptionHandler(final Exception e, final HttpStatus status) {
        String message = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());

        return new ResponseEntity<>(message, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(final ResourceNotFoundException e) {
        return exceptionHandler(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryInUseException.class)
    public ResponseEntity<?> categoryInUseException(final CategoryInUseException e) {
        return exceptionHandler(e, HttpStatus.BAD_REQUEST);
    }
}
