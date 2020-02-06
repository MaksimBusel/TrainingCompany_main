package main.java.com.epam.training.exception;

public class TagException extends RuntimeException{

    public TagException(Throwable cause) {
        super(cause);
    }

    public TagException(String message) {
        super(message);
    }

    public TagException() {
    }
}
