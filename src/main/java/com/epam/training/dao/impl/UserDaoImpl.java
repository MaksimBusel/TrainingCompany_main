package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.UserDao;
import main.java.com.epam.training.mapper.UserRowMapper;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String FIND_BY_LOGIN_AND_PASSWORD ="SELECT * FROM users WHERE login = ? AND password = MD5(?)";
    private static final String FIND_TEACHERS ="SELECT * FROM users WHERE role='teacher'";
    private static final String FIND_COURSE_STUDENTS = "SELECT * FROM users JOIN users_courses " +
            "ON users.user_id=users_courses.user_id WHERE course_id=?";
    private static final String ID_NAME = "user_id=?";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {
        return executeForSingleResult(FIND_BY_LOGIN_AND_PASSWORD, new UserRowMapper(), login, password);
    }

    @Override
    public List<User> getAllTeachers() throws DaoException {
        return executeQuery(FIND_TEACHERS, new UserRowMapper());
    }

    @Override
    public List<User> findCourseStudents(long courseId) throws DaoException {
        return executeQuery(FIND_COURSE_STUDENTS, new UserRowMapper(), courseId);
    }

    @Override
    protected String getIdName(){
        return ID_NAME;
    }

    @Override
    protected String getTableName() {
        return EntityFields.USERS_TABLE;
    }
}
