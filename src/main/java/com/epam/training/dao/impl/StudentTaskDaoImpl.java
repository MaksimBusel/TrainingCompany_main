package main.java.com.epam.training.dao.impl;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.dao.AbstractDao;
import main.java.com.epam.training.dao.Dao;
import main.java.com.epam.training.dao.StudentTaskDao;
import main.java.com.epam.training.entity.StudentTask;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.mapper.StudentTaskRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentTaskDaoImpl extends AbstractDao<StudentTask> implements StudentTaskDao {
    private static final String GET_MY_MARKS_BY_COURSE_ID = "SELECT tasks.*, student_task_id, mark, feedback, file_path " +
            "FROM student_tasks  INNER JOIN tasks " +
            "ON tasks.task_id=student_tasks.task_id WHERE user_id=? " +
            "AND student_tasks.course_id=? AND lock_task=0";
    private static final String EDIT_MARK_AND_FEEDBACK_BY_ID = "UPDATE student_tasks SET mark=?, feedback=? " +
            "WHERE student_task_id=?";
    private static final String FIND_STUDENT_TASKS =" SELECT tasks.*, student_task_id, mark, feedback, file_path FROM tasks " +
            "JOIN student_tasks ON tasks.task_id=student_tasks.task_id " +
            "WHERE user_id=? AND tasks.course_id=? AND lock_task=0";
    private static final String ADD_STUDENT_TASK ="INSERT INTO student_tasks(course_id, task_id, user_id) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID ="SELECT * FROM student_tasks JOIN tasks " +
            "ON tasks.task_id=student_tasks.task_id " +
            "WHERE student_task_id = ? AND lock_task=0";
    private static final String UPDATE_FILE_PATH = "UPDATE student_tasks SET file_path=? WHERE task_id=? AND user_id=?";
    private static final String FIND_BY_TASK_ID_USER_ID = "SELECT * FROM student_tasks WHERE task_id=? AND user_id=?";

    //not use
    private static final String REMOVE_BY_ID ="DELETE FROM student_tasks WHERE student_task_id = ?";

    public StudentTaskDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    protected String getTableName() {
        return EntityFields.STUDENT_TASKS_TABLE;
    }

    @Override
    public Optional<StudentTask> findById(Long id) throws DaoException {
        return executeForSingleResult(FIND_BY_ID, new StudentTaskRowMapper(), id);
    }

    @Override
    public void save(StudentTask item) throws DaoException {

    }

    @Override
    public void removeById(Long id) throws DaoException {
        try(PreparedStatement statement = createStatement(REMOVE_BY_ID, id)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<StudentTask> findMyMarks(long userId, long courseId) throws DaoException {
        return executeQuery(GET_MY_MARKS_BY_COURSE_ID,new StudentTaskRowMapper(), userId, courseId);
    }

    @Override
    public List<StudentTask> findCourseTasks(long courseId) throws DaoException {
        return executeQuery(GET_MY_MARKS_BY_COURSE_ID,new StudentTaskRowMapper(), courseId);
    }

    @Override
    public void updateMarkAndFeedbackById(long taskId, int mark, String feedback) throws DaoException {
        executeUpdate(EDIT_MARK_AND_FEEDBACK_BY_ID, mark, feedback, taskId);
    }

    @Override
    public void updateFilePath(String pathToFile, long taskId, long userId) throws DaoException {
        executeUpdate(UPDATE_FILE_PATH, pathToFile, taskId, userId);
    }

    @Override
    public List<StudentTask> findStudentTask(long studentId, long courseId) throws DaoException {
        return executeQuery(FIND_STUDENT_TASKS, new StudentTaskRowMapper(), studentId, courseId);
    }

    @Override
    public void addStudentTask(long taskId, long courseId, long userId) throws DaoException {
        executeUpdate(ADD_STUDENT_TASK, courseId, taskId, userId);
    }

    @Override
    public boolean findByTaskIdUserId(long userId, long taskId) throws DaoException {
        try(PreparedStatement statement = createStatement(FIND_BY_TASK_ID_USER_ID, taskId, userId);
            ResultSet resultSet = statement.executeQuery()){
            return resultSet.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
