package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.AbstractDao;
import main.java.com.epam.training.dao.CourseDtoDao;
import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.mapper.CourseDtoRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CourseDtoDaoImpl extends AbstractDao<CourseDto> implements CourseDtoDao {
    public static final String FIND_COURSES_WITH_TEACHERS = "SELECT courses.*, first_name, last_name FROM courses " +
            "JOIN users ON teacher_id=user_id AND lock_course=0";

    public CourseDtoDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return EntityFields.COURSE_TABLE;
    }

    @Override
    public Optional<CourseDto> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void save(CourseDto item) throws DaoException {

    }

    @Override
    public void removeById(Long id) throws DaoException {

    }

    @Override
    public List<CourseDto> findCoursesWithTeachersNames() throws DaoException {
        return executeQuery(FIND_COURSES_WITH_TEACHERS,new CourseDtoRowMapper());
    }
}
