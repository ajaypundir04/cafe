package com.springer.nature.exception;

public class CafeServiceException extends RuntimeException {
    public CafeServiceException(String message) {
        super(message);
    }

    public CafeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CafeServiceException(Throwable cause) {
        super(cause);
    }
}
