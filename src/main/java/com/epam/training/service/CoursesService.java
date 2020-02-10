package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.CourseDao;
import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class CoursesService {
    private static final String DATE_ERROR_KEY = "message.coursedateerror";
    private static final String EDIT_SUCCESS_KEY = "message.editcourse";
    private static final String ADD_SUCCESS_KEY = "message.addedcourse";
    private static final String DELETE_SUCCESS_KEY = "message.deletedcourse";
    private DaoHelperFactory daoHelperFactory;

    public CoursesService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public String editCourse(long courseId, long teacherId, String courseName, String description, LocalDate dateFrom,
                           LocalDate dateTo) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDao dao = helper.createCourseDao();
            if (dateFrom.isBefore(dateTo)) {
                dao.updateCourseById(teacherId, courseName, description, dateFrom, dateTo, courseId);
                return EDIT_SUCCESS_KEY;
            } else{
                return DATE_ERROR_KEY;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String addCourse(long teacherId, String name, String description, LocalDate dateFrom, LocalDate dateTo) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDao dao = helper.createCourseDao();
            if (dateFrom.isBefore(dateTo) && dateFrom.isAfter(LocalDate.now())) {
                dao.save(teacherId, name, description, dateFrom, dateTo);
                return ADD_SUCCESS_KEY;
            } else {
                return DATE_ERROR_KEY;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Course> showTeacherCourses(long teacherId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDao dao = helper.createCourseDao();
            return dao.findTeacherCourses(teacherId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String lockCourse(long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            CourseDao dao = helper.createCourseDao();
            dao.removeById(courseId);
            return DELETE_SUCCESS_KEY;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
