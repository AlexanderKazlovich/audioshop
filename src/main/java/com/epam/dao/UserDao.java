package com.epam.dao;

import com.epam.model.Role;
import com.epam.model.User;
import com.epam.util.DatabaseConnector;

import java.sql.*;

public class UserDao {

    public User getUserByUsernamePassword(String username, String password){
        String SELECT_USERS = "SELECT * from user where username = ? and password = ? ";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            User user = new User();
            while (resultSet.next()){
                user.setRole(Role.USER);
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUsername(resultSet.getString("username"));
                user.setBalance(resultSet.getInt("balance"));
            }
            return user;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int registryUser(User user) {
        String INSERT_USERS_SQL = "INSERT INTO user" +
                "  (username, password, email, role) VALUES " +
                " (?, ?, ?, ?);";
        int result = 0;

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole().name());

            result = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
