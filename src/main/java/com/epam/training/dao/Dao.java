package main.java.com.epam.training.dao;

import main.java.com.epam.training.entity.Identifable;
import main.java.com.epam.training.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T extends Identifable> {

    Optional<T> findById(Long id) throws DaoException;
    List<T> findAll()throws DaoException;
    void save(String query, Object... parameters) throws DaoException;
    void removeById(Long id) throws DaoException;
}
