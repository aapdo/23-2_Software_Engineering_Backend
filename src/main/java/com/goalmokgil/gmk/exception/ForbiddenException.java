package com.goalmokgil.gmk.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenException extends RuntimeException {
    private final HttpStatus status;

    public ForbiddenException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
