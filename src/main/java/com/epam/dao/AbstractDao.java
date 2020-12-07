package com.epam.dao;

import com.epam.pool.ProxyConnection;

public class AbstractDao {
    ProxyConnection proxyConnection;

    public AbstractDao(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }
}
