package com.haekyulog.haekyulog.exception;

public abstract class HaekyulogException extends RuntimeException{

    public HaekyulogException(String message) {
        super(message);
    }

    public HaekyulogException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();
}
