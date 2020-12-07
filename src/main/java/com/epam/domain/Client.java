package com.epam.domain;

public class Client extends User {
    private Integer balance;

    //constructor to create user from browser info
    public Client(String username, String password, String email, String firstName, Role role, Integer balance) {
        super(username, password, email, firstName, role);
        this.balance = balance;
    }
    //constructor to create user from batabase info
    public Client(Integer id, String username, String password, String email, String firstName, Role role, Integer balance) {
        super(id, username, password, email, firstName, role);
        this.balance = balance;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Client{" +
                "balance=" + balance +
                '}';
    }
}
