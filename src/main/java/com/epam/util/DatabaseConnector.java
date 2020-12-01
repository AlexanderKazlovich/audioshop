package com.epam.util;










import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class DatabaseConnector {
    private static Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);

    private static final String DATABASE_PROPERTIES_PATH = "database";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USERNAME = "db.username";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final String DATABASE_DRIVER = "db.driver";

    public static Connection getConnection() {
        ResourceBundle rb = ResourceBundle.getBundle(DATABASE_PROPERTIES_PATH);
        try {
            Class.forName(rb.getString(DATABASE_DRIVER));
            Connection connection = DriverManager.getConnection(rb.getString(DATABASE_URL),
                    rb.getString(DATABASE_USERNAME),
                    rb.getString(DATABASE_PASSWORD));
            return connection;
        }catch (ClassNotFoundException e) {
            logger.error("Cannot registry driver");
        }catch (SQLException e) {
            logger.error("Couldn`t get connection to database");
        }
        return  null;
    }
}
