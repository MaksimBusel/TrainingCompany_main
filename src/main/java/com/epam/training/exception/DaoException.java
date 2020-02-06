package main.java.com.epam.training.exception;

import java.sql.SQLException;

public class DaoException extends Exception {
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException() {
    }

    public DaoException(Throwable e) {
    }
}
