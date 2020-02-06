package main.java.com.epam.training.service;

import main.java.com.epam.training.dao.DaoHelper;
import main.java.com.epam.training.dao.DaoHelperFactory;
import main.java.com.epam.training.dao.UserDao;
import main.java.com.epam.training.dao.impl.UserDaoImpl;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.DaoException;
import main.java.com.epam.training.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private DaoHelperFactory daoHelperFactory;

    public UserService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory=daoHelperFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            UserDao dao = helper.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }


    public List<User> showCourseStudents(long courseId) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            UserDaoImpl dao = helper.createUserDao();
            return dao.findCourseStudents(courseId);
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<User> showTeachers() throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            UserDaoImpl dao = helper.createUserDao();
            return dao.getAllTeachers();
        } catch (DaoException e){
            throw new ServiceException(e);
        }

    }
}
