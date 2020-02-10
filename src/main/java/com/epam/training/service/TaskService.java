package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dao.TaskDao;
import main.java.com.epam.training.entity.Task;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private static final String DATE_ERROR_KEY = "message.taskdateerror";
    private static final String EDIT_SUCCESS_KEY = "message.editedtask";
    private static final String ADD_SUCCESS_KEY = "message.addedtask";
    private static final String DELETE_SUCCESS_KEY = "message.deletedtask";
    private DaoHelperFactory daoHelperFactory;

    public TaskService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public String addTask(long courseId, String name, LocalDate dateFrom, LocalDate dateTo) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDao dao = helper.createTaskDao();
            if (dateFrom.isBefore(dateTo) && dateFrom.isAfter(LocalDate.now())) {
                dao.save(courseId, name, dateFrom, dateTo);
                return ADD_SUCCESS_KEY;
            } else {
                return DATE_ERROR_KEY;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Task> showTasksCourse(long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDao dao = helper.createTaskDao();
            return dao.findAllByCourseId(courseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String lockTask(long taskId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDao dao = helper.createTaskDao();
            dao.removeById(taskId);
            return DELETE_SUCCESS_KEY;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public String editTask(long courseId, String name, LocalDate dateFrom, LocalDate dateTo, long taskId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDao dao = helper.createTaskDao();
            if (dateFrom.isBefore(dateTo)) {
                dao.updateById(courseId, name, dateFrom, dateTo, taskId);
                return EDIT_SUCCESS_KEY;
            } else{
                return DATE_ERROR_KEY;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
