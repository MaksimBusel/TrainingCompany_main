package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dao.impl.TaskDaoImpl;
import main.java.com.epam.training.entity.Task;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private DaoHelperFactory daoHelperFactory;

    public TaskService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void addTask(long courseId, String name, LocalDate dateFrom, LocalDate dateTo) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDaoImpl dao = helper.createTaskDao();
            if (dateFrom.isBefore(dateTo) && dateFrom.isAfter(LocalDate.now())) {
                dao.save(courseId, name, dateFrom, dateTo);
            } else {
                throw new RuntimeException(); //change
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Task> showTasksCourse(long courseId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDaoImpl dao = helper.createTaskDao();
            return dao.findAllByCourseId(courseId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void lockTask(long taskId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDaoImpl dao = helper.createTaskDao();
            dao.removeById(taskId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void editTask(long courseId, String name, LocalDate dateFrom, LocalDate dateTo, long taskId) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            TaskDaoImpl dao = helper.createTaskDao();
            if (dateFrom.isBefore(dateTo)) {
                dao.updateById(courseId, name, dateFrom, dateTo, taskId);
            } else {
                throw new RuntimeException(); //change
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
