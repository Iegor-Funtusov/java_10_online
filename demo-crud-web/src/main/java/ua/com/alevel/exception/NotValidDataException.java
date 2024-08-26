package ua.com.alevel.exception;

public class NotValidDataException extends RuntimeException {

    public NotValidDataException(String message) {
        super(message);
    }
}
