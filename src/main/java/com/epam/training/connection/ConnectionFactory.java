package main.java.com.epam.training.connection;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String PATH_TO_PROPERTIES = "src/main/resources/database.properties";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    public static ProxyConnection create(ConnectionPool pool) {
        Connection connection = null;
        try {
            connection = getConnection();
        } catch (SQLException | IOException e) {
            e.printStackTrace(); //throw runtime exception
        }
        return new ProxyConnection(connection, pool);
    }

    private static Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream input = Files.newInputStream(Paths.get(PATH_TO_PROPERTIES))) {
            properties.load(input);
        }
        String url = properties.getProperty(URL);
        String username = properties.getProperty(USERNAME);
        String password = properties.getProperty(PASSWORD);

        return DriverManager.getConnection(url, username, password);
    }
}
