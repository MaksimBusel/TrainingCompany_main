package main.java.com.epam.training.mapper;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dto.StudentTaskDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentTaskDtoRowMapper implements RowMapper<StudentTaskDto> {

    @Override
    public StudentTaskDto map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(EntityFields.STUDENT_TASK_ID);
        long userId = resultSet.getLong(EntityFields.USER_ID);
        long taskId = resultSet.getLong(EntityFields.TASK_ID);
        String studentFirstName = resultSet.getString(EntityFields.FIRST_NAME);
        String studentLastName = resultSet.getString(EntityFields.LAST_NAME);
        String taskName = resultSet.getString(EntityFields.TASK_NAME);
        int mark = resultSet.getInt(EntityFields.MARK);
        String feedback = resultSet.getString(EntityFields.FEEDBACK);
        String filePath = resultSet.getString(EntityFields.FILE_PATH);

        return new StudentTaskDto(id, userId, taskId, studentFirstName, studentLastName, taskName, mark, feedback, filePath);
    }
}
