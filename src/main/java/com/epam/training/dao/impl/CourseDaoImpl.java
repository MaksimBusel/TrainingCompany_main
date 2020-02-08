package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.CourseDao;
import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.NotSupportedOperationException;
import main.java.com.epam.training.mapper.CourseRowMapper;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {
    private static final String FIND_BY_ID ="SELECT * FROM courses WHERE course_id = ? AND lock_course=0";
    private static final String LOCK_BY_ID ="UPDATE courses SET lock_course=1 WHERE course_id = ?";
    private static final String FIND_TEACHER_COURSES ="SELECT * FROM courses WHERE teacher_id=? AND lock_course=0";
    private static final String EDIT_COURSE_BY_ID = "UPDATE courses SET teacher_id=?, course_name=?, description=?, " +
            "date_from=?, date_to=? WHERE course_id =?";
    private static final String ADD_COURSE = "INSERT INTO courses (teacher_id, course_name, description, date_from, date_to)" +
            " VALUES(?, ?, ?, ?, ?)";
    private static final String ID_NAME = "course_id=?";


    public CourseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Course> findById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new CourseRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
       executeUpdate(LOCK_BY_ID, id);
    }

    @Override
    public void save(long teacherId, String name, String description, LocalDate dateFrom, LocalDate dateTo) throws DaoException {
        executeUpdate(ADD_COURSE, teacherId, name, description, dateFrom, dateTo);
    }

    @Override
    public void updateCourseById(long teacherId, String courseName, String description, LocalDate dateFrom, LocalDate dateTo,
                                 long courseId) throws DaoException {
        executeUpdate(EDIT_COURSE_BY_ID, teacherId, courseName, description, dateFrom, dateTo, courseId);
    }

    @Override
    public List<Course> findTeacherCourses(long teacherId) throws DaoException {
        return executeQuery(FIND_TEACHER_COURSES, new CourseRowMapper(), teacherId);
    }

    @Override
    protected String getTableName() {
        return EntityFields.COURSE_TABLE;
    }

    protected String getIdName(){
        return ID_NAME;
    }
}
