package main.java.com.epam.training.mapper;

import main.java.com.epam.training.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifable> {

    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends  Identifable> create(String table){
        switch (table){
            case User.TABLE: return new UserRowMapper();
            case StudentTask.TABLE: return new StudentTaskRowMapper();
            case Task.TABLE: return new TaskRowMapper();
            case Course.TABLE: return new CourseRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table = "+ table);
        }
    }
}
