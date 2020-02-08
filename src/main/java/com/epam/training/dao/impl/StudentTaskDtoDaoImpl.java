package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.StudentTaskDtoDao;
import main.java.com.epam.training.dto.StudentTaskDto;
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
    private static final String ID_NAME = "student_task_id=?";
    private static final String TABLES_ERROR = "You can't get a result from a single table. Data is stored in multiple tables.";

    public StudentTaskDtoDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<StudentTaskDto> findStudentHaveTaskByTaskId(long taskId) throws DaoException {
        return executeQuery(FIND_STUDENTS_HAVE_TASK, new StudentTaskDtoRowMapper(), taskId);
    }

    @Override
    protected String getIdName(){
        return ID_NAME;
    }

    @Override
    protected String getTableName() {
        return EntityFields.STUDENT_TASKS_TABLE;
    }

    @Override
    public Optional<StudentTaskDto> findById(Long id){
        throw new UnsupportedOperationException(TABLES_ERROR);
    }
}
