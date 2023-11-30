package com.goalmokgil.gmk.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<String> handleForbiddenCourseException(ForbiddenException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }
}
