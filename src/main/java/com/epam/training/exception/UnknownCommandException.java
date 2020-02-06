package main.java.com.epam.training.exception;

public class UnknownCommandException extends Exception{

    public UnknownCommandException(Throwable cause) {
        super(cause);
    }

    public UnknownCommandException(String message) {
        super(message);
    }

    public UnknownCommandException() {
    }
}
