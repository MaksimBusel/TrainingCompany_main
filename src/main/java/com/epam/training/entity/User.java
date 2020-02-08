package main.java.com.epam.training.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Identifable, Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String login;
    private UserRole role;

    public User(long id, String firstName, String lastName, String login, UserRole role) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User() {
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id &&
                firstName != null ? firstName.equals(user.firstName) : user.firstName == null &&
                lastName != null ? lastName.equals(user.lastName) : user.lastName == null &&
                login != null ? login.equals(user.login) : user.login == null &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, login, role);
    }

    @Override
    public String toString() {
        return "User{" +"id=" + id + ", login='" + login + ", firstName='" + firstName +
                ", lastName='" + lastName + ", role=" + role + '}';
    }
}
