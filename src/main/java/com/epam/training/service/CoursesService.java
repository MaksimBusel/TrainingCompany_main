package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.impl.CourseDaoImpl;
import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CoursesService {
    private DaoHelperFactory daoHelperFactory;

    public CoursesService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void editCourse(long courseId, long teacherId, String courseName, String description, LocalDate dateFrom,
                           LocalDate dateTo) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDaoImpl dao = helper.createCourseDao();
            if (dateFrom.isBefore(dateTo)) {
                dao.updateCourseById(teacherId, courseName, description, dateFrom, dateTo, courseId);
            } else{
                throw new RuntimeException(); //change
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void addCourse(long teacherId, String name, String description, LocalDate dateFrom, LocalDate dateTo) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDaoImpl dao = helper.createCourseDao();
            if (dateFrom.isBefore(dateTo) && dateFrom.isAfter(LocalDate.now())) {
                dao.save(teacherId, name, description, dateFrom, dateTo);
            } else {
                throw new RuntimeException(); //change
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Course> showTeacherCourses(long teacherId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDaoImpl dao = helper.createCourseDao();
            return dao.findTeacherCourses(teacherId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void lockCourse(long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDaoImpl dao = helper.createCourseDao();
            dao.removeById(courseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
