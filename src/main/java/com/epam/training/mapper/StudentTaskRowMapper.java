package main.java.com.epam.training.mapper;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTaskRowMapper implements RowMapper<StudentTask>{

    @Override
    public StudentTask map(ResultSet resultSet) throws SQLException {
        long taskId = resultSet.getLong(EntityFields.TASK_ID);
        long studentTaskId = resultSet.getLong(EntityFields.STUDENT_TASK_ID);
        String name = resultSet.getString(EntityFields.TASK_NAME);
        String dateFrom = resultSet.getString(EntityFields.DATE_FROM);
        String dateTo = resultSet.getString(EntityFields.DATE_TO);
        Integer mark = resultSet.getInt(EntityFields.MARK);
        String feedback = resultSet.getString(EntityFields.FEEDBACK);
        String filePath = resultSet.getString(EntityFields.FILE_PATH);

        return new StudentTask(taskId, studentTaskId, name, dateFrom, dateTo, mark, feedback, filePath);
    }
}
