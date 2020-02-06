package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dao.impl.StudentCourseDaoImpl;
import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class StudentCourseService {
    private DaoHelperFactory daoHelperFactory;

    public StudentCourseService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<CourseDto> showMyCourses(long id) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDaoImpl dao = helper.createStudentCourseDao();
            return dao.getCoursesById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void enroll(long userId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDaoImpl dao = helper.createStudentCourseDao();
            boolean resultCheck = dao.findStudentEnrolledInCourse(userId, courseId);
            if (!resultCheck) {
                dao.enrollStudentInCourse(userId, courseId);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void unenroll(long userId, long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            StudentCourseDaoImpl dao = helper.createStudentCourseDao();
            dao.unenrollStudentFromCourse(userId, courseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}