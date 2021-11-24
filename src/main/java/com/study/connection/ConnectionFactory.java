package com.study.connection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Data
@AllArgsConstructor
@Slf4j
public class ConnectionFactory {
    static final String DRIVER = "driver";
    static final String URL = "url";
    static final String USER = "user";
    static final String PASSWORD = "password";

    private Properties properties;

    public Connection getConnection() {
        Connection connection;
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
