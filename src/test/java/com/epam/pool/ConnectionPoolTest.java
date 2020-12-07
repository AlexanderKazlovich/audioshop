package com.epam.pool;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

public class ConnectionPoolTest {

    @Test
    public void getConnection() {
        ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
        assertNotNull(connection);
    }
    @Test
    public void destroyPool(){
        ConnectionPool.INSTANCE.destroyPool();
    }
}