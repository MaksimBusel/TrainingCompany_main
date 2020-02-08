package main.java.com.epam.training.mapper;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifable> {
    String UNKNOWN_TABLE = "Unknown table = ";

    T map(ResultSet resultSet) throws SQLException;

    static RowMapper<? extends  Identifable> create(String table){
        switch (table){
            case EntityFields.USERS_TABLE: return new UserRowMapper();
            case EntityFields.STUDENT_TASKS_TABLE: return new StudentTaskRowMapper();
            case EntityFields.TASKS_TABLE: return new TaskRowMapper();
            case EntityFields.COURSE_TABLE: return new CourseRowMapper();
            default:
                throw new IllegalArgumentException(UNKNOWN_TABLE + table);
        }
    }
}
