package com.goalmokgil.gmk.exception;

public class CustomExpiredJwtException extends RuntimeException {
    public CustomExpiredJwtException(String msg) {
        super(msg);
    }
}