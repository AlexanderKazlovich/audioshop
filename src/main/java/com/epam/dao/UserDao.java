package com.epam.dao;

import com.epam.domain.Client;
import com.epam.domain.Role;
import com.epam.domain.User;
import com.epam.pool.ProxyConnection;
import com.epam.util.Password;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDao extends AbstractDao {
    public UserDao(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }
    public final String INSERT_USER = "INSERT INTO app_user(username, password, first_name, email, role_id) value(?, ?, ?, ?, ?)";
    public final String INSERT_CLIENT = "INSERT INTO client(balance) values (1000)";
    public final String SELECT_USER_CLIENT = "select id, username, password, email, first_name, balance  from app_user join client on app_user.id = client.user_id where password = ? and username = ?";

    public boolean addUser(User user){
        String hashPass = Password.INSTANCE.hashPassword(user.getPassword());
        try(PreparedStatement preparedStatementUser = proxyConnection.prepareStatement(INSERT_USER);
            PreparedStatement preparedStatementClient = proxyConnection.prepareStatement(INSERT_USER)
            ){
            preparedStatementUser.setString(1, user.getUsername());
            preparedStatementUser.setString(2, hashPass);
            preparedStatementUser.setString(3, user.getFirstName());
            preparedStatementUser.setString(4, user.getEmail());
            preparedStatementUser.setInt(5, 1);
            if(!preparedStatementUser.execute()) {
                return !preparedStatementClient.execute(INSERT_CLIENT);
            }
            return true;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User findClientByUsernameAndPassword(String username, String password){
        try(PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_USER_CLIENT);){

            preparedStatement.setString(1, Password.INSTANCE.hashPassword(password));
            preparedStatement.setString(2, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null){return null;}
            return createUser(resultSet);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User createUser(ResultSet resultSet){
        try {
            User user = new Client(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    Role.resolveRole(resultSet.getInt(6)),
                    resultSet.getInt(7)
            );
            return user;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
