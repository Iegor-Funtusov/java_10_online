package ua.com.alevel.exception;

public class InvalidApiVersionException extends RuntimeException {
    public InvalidApiVersionException(String message) {
        super(message);
    }
}
