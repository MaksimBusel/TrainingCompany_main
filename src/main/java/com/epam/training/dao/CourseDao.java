package main.java.com.epam.training.dao;

import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.exception.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface CourseDao extends Dao<Course>{

    void save(long teacherId, String name, String description, LocalDate dateFrom, LocalDate dateTo) throws DaoException;

    void updateCourseById(long teacherId, String courseName, String description, LocalDate dateFrom, LocalDate dateTo,
                          long courseId) throws DaoException;

    List<Course> findTeacherCourses(long teacherId) throws DaoException;
}
