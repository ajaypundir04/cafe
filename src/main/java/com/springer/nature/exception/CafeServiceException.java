package com.springer.nature.exception;

/**
 * @author Ajay Singh Pundir
 * This class deals with the application level exception
 */
public class CafeServiceException extends RuntimeException {
    public CafeServiceException(String message) {
        super(message);
    }
}
