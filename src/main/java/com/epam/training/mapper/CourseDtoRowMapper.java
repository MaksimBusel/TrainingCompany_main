package main.java.com.epam.training.mapper;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dto.CourseDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDtoRowMapper implements RowMapper<CourseDto>  {

    @Override
    public CourseDto map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(EntityFields.COURSE_ID);
        String name = resultSet.getString(EntityFields.COURSE_NAME);
        String description = resultSet.getString(EntityFields.DESCRIPTION);
        String dateFrom = resultSet.getString(EntityFields.DATE_FROM);
        String dateTo = resultSet.getString(EntityFields.DATE_TO);
        String teacherFirstName = resultSet.getString(EntityFields.FIRST_NAME);
        String teacherLastName = resultSet.getString(EntityFields.LAST_NAME);
        long teacherId = resultSet.getLong(EntityFields.TEACHER_ID);

        return new CourseDto(id, name, description, dateFrom, dateTo, teacherFirstName, teacherLastName, teacherId);
    }
}
