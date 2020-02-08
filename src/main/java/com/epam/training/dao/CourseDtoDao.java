package main.java.com.epam.training.dao;

import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.exception.DaoException;

import java.util.List;

public interface CourseDtoDao extends Dao<CourseDto>{

    List<CourseDto> findCoursesWithTeachersNames() throws DaoException;
}
