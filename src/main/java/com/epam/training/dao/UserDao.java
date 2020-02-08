package main.java.com.epam.training.dao;

import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends Dao<User>{

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    List<User> getAllTeachers() throws DaoException;

    List<User> findCourseStudents(long courseId) throws DaoException;
}
