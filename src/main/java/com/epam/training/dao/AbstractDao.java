package main.java.com.epam.training.dao;

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

    protected AbstractDao(Connection connection){
        this.connection=connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException{
        List<T> entities = new ArrayList<>();
        try(PreparedStatement statement = createStatement(query, params); ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entities;
    }

    protected void executeUpdate(String query, Object... params) throws DaoException{
        try(PreparedStatement statement = createStatement(query, params)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected PreparedStatement createStatement(String query, Object... params)throws SQLException{
        PreparedStatement statement = connection.prepareStatement(query);
        int arrayLength = params.length;
        for (int i = 1; i <= arrayLength; i++) {
            statement.setObject(i, params[i-1]);
        }
        return statement;
    }

    protected Optional<T> executeForSingleResult(String query, RowMapper<T> builder, Object... params) throws DaoException {
        List<T> items = executeQuery(query, builder, params);
        if(items.size()==1){
            return Optional.of(items.get(0));
        } else if(items.size()>1){
            throw new IllegalArgumentException("More than one record found");
        }else {
            return Optional.empty();
        }
    }

    public List<T> findAll() throws DaoException{
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>)RowMapper.create(table);
        return executeQuery(FIND_ALL + table, mapper);
    }

    protected abstract String getTableName();
}
