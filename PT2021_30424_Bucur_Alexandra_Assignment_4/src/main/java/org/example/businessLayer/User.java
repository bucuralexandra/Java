package org.example.businessLayer;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Alexandra
 * @class User
 * @since 13/03/2021
 */
public class User implements Serializable {

    private String username;
    private String password;
    private UserType userType;
    private UUID userId;

    /**
     * constructor
     *
     * @param username of the client
     * @param password of the client
     * @param userType type of the client
     */
    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.userId = UUID.randomUUID();
    }

    public UUID getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    /**
     * method that checks if 2 objects of type user are equal
     *
     * @param o parameter to be checked
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, userType);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                "id= " + userId +
                '}';
    }

    public String toStringReports() {
        return "username= " + username;

    }

}
