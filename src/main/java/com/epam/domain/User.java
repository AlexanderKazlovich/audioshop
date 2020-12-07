package com.epam.domain;

public class User {
    private static Integer countId = 1;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private Role role;

    //constructor to create user from browser info
    public User(String username, String password, String email, String firstName, Role role) {
        this.id = countId++;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.role = role;
    }
    //constructor to create user from batabase info
    public User(Integer id, String username, String password, String email, String firstName, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.role = role;
    }
    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "  username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", role=" + role +
                '}';
    }
}
