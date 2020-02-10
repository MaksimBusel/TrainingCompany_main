package main.java.com.epam.training.exception;

public class NotSupportedOperationException extends RuntimeException {

    public NotSupportedOperationException(Throwable cause) {
        super(cause);
    }

    public NotSupportedOperationException(String message) {
        super(message);
    }

    public NotSupportedOperationException() {
    }
}
