package com.study.connection;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Data
@Slf4j
public class ConnectionFactory {
    private final String DRIVER = "driver";
    private final String URL = "url";
    private final String USER = "user";
    private final String PASSWORD = "password";
    private Properties properties;

    public Connection getConnection() {
        Connection connection;
        try {
            Class.forName(properties.getProperty(DRIVER));
        } catch (ClassNotFoundException e) {
            log.info("Can't load driver!", e);
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection(properties.getProperty(URL),
                    properties.getProperty(USER), properties.getProperty(PASSWORD));
        } catch (SQLException e) {
            log.info("Database access error!", e);
            throw new RuntimeException(e);
        }
        return connection;
    }
}