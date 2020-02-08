package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.StudentCourseDao;
import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.entity.Identifable;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.mapper.CourseDtoRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentCourseDaoImpl extends AbstractDao<CourseDto> implements StudentCourseDao {
    private static final String WHERE_COURSES_ID_EQUALS_STUDENT_ID = " WHERE course_id IN (SELECT course_id " +
            "FROM users_courses WHERE user_id=?)";
    private static final String ENROLL_STUDENT_IN_COURSE = "INSERT INTO users_courses (user_id, course_id) VALUES(?,?)";
    private static final String REMOVE_STUDENT_FROM_COURSE = "DELETE FROM users_courses WHERE user_id=? AND course_id=?";
    private static final String FIND_STUDENT_ENROLLED_IN_COURSE = "SELECT * FROM users_courses WHERE user_id=? AND course_id=?";
    private static final String ID_ERROR = "The table doesn't have a unique identifier";

    public StudentCourseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<CourseDto> findCoursesById(Long id) throws DaoException {
       String coursesInfo= CourseDtoDaoImpl.FIND_COURSES_WITH_TEACHERS;
       return executeQuery(coursesInfo + WHERE_COURSES_ID_EQUALS_STUDENT_ID, new CourseDtoRowMapper(), id);
    }

    @Override
    public void enrollStudentInCourse(long userId, long courseId) throws DaoException {
        executeUpdate(ENROLL_STUDENT_IN_COURSE, userId, courseId);
    }

    @Override
    public boolean findStudentEnrolledInCourse(long studentId, long courseId) throws DaoException {
        try(PreparedStatement statement = createStatement(FIND_STUDENT_ENROLLED_IN_COURSE, studentId, courseId);
            ResultSet resultSet = statement.executeQuery()){
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void unenrollStudentFromCourse(long userId, long courseId) throws DaoException {
        executeUpdate(REMOVE_STUDENT_FROM_COURSE, userId, courseId);
    }

    @Override
    protected String getIdName(){
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<CourseDto> findById(Long id){
        throw new UnsupportedOperationException(ID_ERROR);
    }

    @Override
    protected String getTableName() {
        return EntityFields.USERS_COURSES_TABLE;
    }
}
