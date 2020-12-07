package com.epam.pool;

import com.epam.properties.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InitializerConnectionPool {
    private static Logger logger = LoggerFactory.getLogger(InitializerConnectionPool.class);
    private static ApplicationProperties applicationProperties = ApplicationProperties.getApplicationProperties();
    static {
        try {
            Class.forName(applicationProperties.getDriver());
        } catch (ClassNotFoundException e) {
            logger.error("Cannot find driver class");
        }
    }

    public static Connection createConnection(){
        try {
            Connection connection = DriverManager.getConnection(
                    applicationProperties.getUrl(),
                    applicationProperties.getUsername(),
                    applicationProperties.getPassword()
            );
            return connection;
        } catch (SQLException throwables) {
            logger.error("Cannot connect to database");
        }
        return null;
    }
}
