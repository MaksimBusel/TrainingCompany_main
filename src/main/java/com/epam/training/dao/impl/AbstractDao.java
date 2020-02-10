package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.dao.Dao;
import main.java.com.epam.training.mapper.RowMapper;
import main.java.com.epam.training.entity.Identifable;
import main.java.com.epam.training.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Identifable> implements Dao<T> {
    private Connection connection;
    private static final String FIND_ALL = "SELECT * FROM ";
    private static final String REMOVE_BY_ID = "DELETE FROM";
    private static final String WHERE = "WHERE ";
    private static final String NUMBER_RECORDS_ERROR = "More than one record found";

    AbstractDao(Connection connection) {
        this.connection = connection;
    }

    List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, params); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entities;
    }

    void executeUpdate(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        int arrayLength = params.length;
        for (int i = 1; i <= arrayLength; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    Optional<T> executeForSingleResult(String query, RowMapper<T> builder, Object... params) throws DaoException {
        List<T> items = executeQuery(query, builder, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new UnsupportedOperationException(NUMBER_RECORDS_ERROR);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery(FIND_ALL + table, mapper);
    }

    @Override
    public void save(String query, Object... parameters) throws DaoException {
        executeUpdate(query, parameters);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        String table = getTableName();
        String idName = getIdName();
        executeUpdate(REMOVE_BY_ID + table + WHERE + idName, id);
    }

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        String table = getTableName();
        String idName = getIdName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeForSingleResult(FIND_ALL + table + WHERE + idName, mapper, id);
    }

    protected abstract String getTableName();

    protected abstract String getIdName();
}
