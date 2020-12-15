package com.epam.repository;

import com.epam.domain.user.Client;
import com.epam.domain.user.Role;
import com.epam.domain.user.User;
import com.epam.pool.ProxyConnection;
import com.epam.util.Password;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    public UserDao(ProxyConnection proxyConnection) {
        super(proxyConnection);
    }

    public final String INSERT_USER = "INSERT INTO app_user(username, password, first_name, email, role_id) value(?, ?, ?, ?, ?)";
    public final String INSERT_CLIENT = "INSERT INTO client(username, balance) value (?, 1000)";
    public final String SELECT_USER_CLIENT = "select * from app_user left join client using(username) where username = ?";
    public final String SELECT_ALL_USERS = "select *  from app_user left join client using(username)";
    public final String DELETE_USER = "delete from app_user where username = ?";
    public final String DELETE_CLIENT = "delete from client where username = ?";
    @Override
    public void delete(String username) {
        try(PreparedStatement preparedStatementUser = proxyConnection.prepareStatement(DELETE_USER);
            PreparedStatement preparedStatementClient = proxyConnection.prepareStatement(DELETE_CLIENT)
        ){
            preparedStatementUser.execute();
            preparedStatementClient.execute();
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }

    public boolean save(String username, String password, String email, String first_name, Role role, int balance ){
        String hashPass = Password.INSTANCE.hashPassword(password);
        try(PreparedStatement preparedStatementUser = proxyConnection.prepareStatement(INSERT_USER);
            PreparedStatement preparedStatementClient = proxyConnection.prepareStatement(INSERT_CLIENT)
            ){
            preparedStatementUser.setString(1, username);
            preparedStatementUser.setString(2, hashPass);
            preparedStatementUser.setString(3, first_name);
            preparedStatementUser.setString(4, email);
            preparedStatementUser.setInt(5, Role.resolveId(role));
            if(!preparedStatementUser.execute()) {
                preparedStatementClient.setString(1, username);
                return !preparedStatementClient.execute();
            }
            return false;
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User findUserByUsernameAndPassword(String username, String password){
        try(PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_USER_CLIENT)){
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return createUser(resultSet, password);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        try(PreparedStatement preparedStatement = proxyConnection.prepareStatement(SELECT_USER_CLIENT)){
            ResultSet resultSet = preparedStatement.executeQuery();
            return createUserList(resultSet);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private List<User> createUserList(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                User user = new Client(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        null,
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        Role.resolveRole(resultSet.getInt("role_id")),
                        resultSet.getInt("balance"));
                users.add(user);
            }
            return users;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User createUser(ResultSet resultSet, String password){
        try {
            while (resultSet.next()) {
                boolean comparePasswords = Password.checkPassword(password, resultSet.getString(3));
                if (!comparePasswords){return null;}
                Role role = Role.resolveRole(resultSet.getInt(6));
                if (role == Role.CLIENT){
                    User client = new Client(resultSet.getInt(1), resultSet.getString(2), password, resultSet.getString(4), resultSet.getString(5), role, resultSet.getInt(7));
                    return client;
                }
                if (role == Role.ADMIN){
                    User admin = new User(resultSet.getInt(1), resultSet.getString(2), password, resultSet.getString(4), resultSet.getString(5), role);
                    return admin;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
