package com.springer.nature.exception;

public class MenuServiceException extends RuntimeException {
    public MenuServiceException(String message) {
        super(message);
    }

    public MenuServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenuServiceException(Throwable cause) {
        super(cause);
    }
}
