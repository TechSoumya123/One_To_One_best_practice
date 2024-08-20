package com.developer.soumya.One_To_One.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception
                        .getConstraintViolations()
                        .stream()
                        .map(cv -> isNull(cv) ? "null" : cv.getMessage())
                        .collect(Collectors.joining(",")));
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpRequestMethod(HttpRequestMethodNotSupportedException exception) {
        String message = "Sorry, Request Method " + exception.getMethod() + " is not supported, Supported method is " + exception.getSupportedHttpMethods().stream().findFirst().get();
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException mismatchException) {
        String message = "Failed to convert value of type " + mismatchException.getValue().getClass().getSimpleName() + " to required type " + mismatchException.getRequiredType().getSimpleName() + " For input string: " + mismatchException.getValue();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Object> handleStaticResource(NoResourceFoundException noResourceFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Sorry, we can't operate the operation. Please check the actual path ");
    }

}
