package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.TaskDao;
import main.java.com.epam.training.entity.Task;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.mapper.TaskRowMapper;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TaskDaoImpl extends AbstractDao<Task> implements TaskDao {
    private static final String SAVE_TASK = "INSERT INTO tasks (course_id, task_name, date_from, date_to) VALUES(?, ?, ?, ?)";
    private static final String LOCK_BY_ID = "UPDATE tasks SET lock_task=1 WHERE task_id = ?";
    private static final String EDIT_TASK_BY_ID = "UPDATE tasks SET course_id=?, task_name=?, date_from=?, date_to=?" +
            " WHERE task_id =?";
    private static final String FIND_ALL_BY_COURSE_ID = "SELECT * FROM tasks WHERE course_id=? AND lock_task=0";
    private static final String ID_NAME = "task_id=?";
    private static final String FIND_BY_ID = "SELECT * FROM tasks WHERE task_id = ? AND lock_task=0";

    public TaskDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Task> findById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new TaskRowMapper(), id);
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(LOCK_BY_ID, id);
    }

    @Override
    public void save(long courseId, String name, LocalDate dateFrom, LocalDate dateTo) throws DaoException {
        executeUpdate(SAVE_TASK, courseId, name, dateFrom, dateTo);
    }

    @Override
    public void updateById(long courseId, String name, LocalDate dateFrom, LocalDate dateTo, long taskId) throws DaoException {
        executeUpdate(EDIT_TASK_BY_ID,courseId, name, dateFrom, dateTo, taskId);
    }

    @Override
    public List<Task> findAllByCourseId(long courseId) throws DaoException {
        return executeQuery(FIND_ALL_BY_COURSE_ID, new TaskRowMapper(), courseId);
    }

    protected String getIdName(){
        return ID_NAME;
    }

    @Override
    protected String getTableName() {
        return EntityFields.STUDENT_TASKS_TABLE;
    }
}
