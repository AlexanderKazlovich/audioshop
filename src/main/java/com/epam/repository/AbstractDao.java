package com.epam.repository;

import com.epam.domain.Entity;
import com.epam.pool.ProxyConnection;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T extends Entity> {
    ProxyConnection proxyConnection;
    public AbstractDao(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public abstract List<T> findAll();
    public abstract void delete(String username);
    public abstract void update(T t);

}
