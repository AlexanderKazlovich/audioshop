package com.epam.domain;

public enum Role {
    CLIENT, ADMIN;

    public static Role resolveRole(int id){
        if (id == 1) return CLIENT;
        if (id == 2) return ADMIN;
        else throw new IllegalArgumentException("Cannot resolve Role by id");
    }
}
