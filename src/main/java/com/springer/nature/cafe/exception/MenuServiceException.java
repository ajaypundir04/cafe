package com.springer.nature.cafe.exception;

/**
 * @author Ajay Singh Pundir
 * This class deals with the application level exception
 */
public class MenuServiceException extends RuntimeException {
    public MenuServiceException(String message) {
        super(message);
    }
}
