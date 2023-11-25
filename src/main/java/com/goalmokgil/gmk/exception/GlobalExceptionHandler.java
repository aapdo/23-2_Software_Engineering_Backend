package com.goalmokgil.gmk.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ForbiddenCourseException.class)
    public ResponseEntity<String> handleForbiddenCourseException(ForbiddenCourseException exception) {
        return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
    }
}
