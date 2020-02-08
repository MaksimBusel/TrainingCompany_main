package main.java.com.epam.training.mapper;

import main.java.com.epam.training.constant.EntityFields;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.entity.UserRole;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(EntityFields.USER_ID);
        String firstName = resultSet.getString(EntityFields.FIRST_NAME);
        String lastName = resultSet.getString(EntityFields.LAST_NAME);
        String login = resultSet.getString(EntityFields.LOGIN);
        String role = resultSet.getString(EntityFields.ROLE);
        UserRole userRole = UserRole.valueOf(role.toUpperCase());

        return new User(id, firstName, lastName, login, userRole);
    }
}
