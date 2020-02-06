package main.java.com.epam.training.dao;

import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.exception.DaoException;

import java.util.List;

public interface StudentTaskDao extends Dao<StudentTask>{

    List<StudentTask> findMyMarks(long userId, long courseId) throws DaoException;

    List<StudentTask> findCourseTasks(long courseId) throws DaoException;

    void updateMarkAndFeedbackById(long taskId, int mark, String feedback) throws DaoException;

    void updateFilePath(String pathToFile, long taskId, long userId) throws DaoException;

    List<StudentTask> findStudentTask(long studentId, long courseId) throws DaoException;

    void addStudentTask(long taskId, long courseId, long userId) throws DaoException;

    boolean findByTaskIdUserId(long userId, long taskId) throws DaoException;
}
