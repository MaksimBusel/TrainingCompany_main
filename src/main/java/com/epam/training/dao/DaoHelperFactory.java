package main.java.com.epam.training.dao;

import main.java.com.epam.training.connection.ConnectionPool;

public class DaoHelperFactory {
    public DaoHelper create(){
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
