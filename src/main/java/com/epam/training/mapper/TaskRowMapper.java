package main.java.com.epam.training.mapper;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRowMapper implements RowMapper<Task> {


    @Override
    public Task map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(EntityFields.TASK_ID);
        String name = resultSet.getString(EntityFields.TASK_NAME);
        String dateFrom = resultSet.getString(EntityFields.DATE_FROM);
        String dateTo = resultSet.getString(EntityFields.DATE_TO);

        return new Task(id, name, dateFrom, dateTo);
    }
}
