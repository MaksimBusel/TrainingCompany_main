package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.AbstractDao;
import main.java.com.epam.training.dao.StudentTaskDtoDao;
import main.java.com.epam.training.dto.StudentTaskDto;
import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.mapper.StudentTaskDtoRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class StudentTaskDtoDaoImpl extends AbstractDao<StudentTaskDto> implements StudentTaskDtoDao {
    private static final String FIND_STUDENTS_HAVE_TASK = "SELECT first_name, last_name, task_name, student_tasks.* " +
    "FROM student_tasks JOIN users ON student_tasks.user_id=users.user_id " +
     "JOIN tasks ON tasks.task_id=student_tasks.task_id" +
     " WHERE student_tasks.task_id=? AND lock_task=0";

    public StudentTaskDtoDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return EntityFields.STUDENT_TASKS_TABLE;
    }

    @Override
    public Optional<StudentTaskDto> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public void save(StudentTaskDto item) throws DaoException {

    }

    @Override
    public void removeById(Long id) throws DaoException {

    }

    @Override
    public List<StudentTaskDto> findStudentHaveTaskByTaskId(long taskId) throws DaoException {
        return executeQuery(FIND_STUDENTS_HAVE_TASK, new StudentTaskDtoRowMapper(), taskId);
    }
}
