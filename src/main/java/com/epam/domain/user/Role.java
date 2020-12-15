package com.epam.domain.user;

public enum Role {
    CLIENT, ADMIN;

    public static Role resolveRole(int id){
        if (id == 1) return CLIENT;
        if (id == 2) return ADMIN;
        else throw new IllegalArgumentException("Cannot resolve Role by id");
    }
    public static int resolveId(Role role){
        if (role == Role.CLIENT) return 1;
        if (role == Role.ADMIN) return 2;
        else throw new IllegalArgumentException("Cannot resolve id by Role");
    }
}
