package com.goalmokgil.gmk.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenCourseException extends RuntimeException {
    private final HttpStatus status;

    public ForbiddenCourseException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
