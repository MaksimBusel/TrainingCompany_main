package main.java.com.epam.training.mapper;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(EntityFields.COURSE_ID);
        String name = resultSet.getString(EntityFields.COURSE_NAME);
        String description = resultSet.getString(EntityFields.DESCRIPTION);
        String dateFrom = resultSet.getString(EntityFields.DATE_FROM);
        String dateTo = resultSet.getString(EntityFields.DATE_TO);
        String teacherId = resultSet.getString(EntityFields.TEACHER_ID);

        return new Course(id, name, description, dateFrom, dateTo, teacherId);
    }
}
