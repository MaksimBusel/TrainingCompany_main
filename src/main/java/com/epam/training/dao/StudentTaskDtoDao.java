package main.java.com.epam.training.dao;

import main.java.com.epam.training.dto.StudentTaskDto;
import main.java.com.epam.training.exception.DaoException;

import java.util.List;

public interface StudentTaskDtoDao extends Dao<StudentTaskDto>{

    List<StudentTaskDto> findStudentHaveTaskByTaskId(long taskId) throws DaoException;
}
