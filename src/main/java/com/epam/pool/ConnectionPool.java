package com.epam.pool;

import com.epam.properties.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public enum ConnectionPool {
    INSTANCE;
    private static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);

    private BlockingQueue<ProxyConnection> pool;

    private ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
    private final int DEFAULT_POOL_SIZE = 32;
    ConnectionPool(){
        pool = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        fillConnectionPool();
    }

    private void fillConnectionPool() {
        for(int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            Connection connection = InitializerConnectionPool.createConnection();
            pool.offer(new ProxyConnection(connection));
        }
    }

    public ProxyConnection getConnection(){
        ProxyConnection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            logger.error("Cannot take connection from pool");
        }
        return connection;
    }
    public void releaseConnection(ProxyConnection connection){
            pool.offer(connection);
    }
    public void destroyPool(){
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++){
            try {
                pool.take().reallyClose();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        deregisterDrivers();
    }
    private void deregisterDrivers(){
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
