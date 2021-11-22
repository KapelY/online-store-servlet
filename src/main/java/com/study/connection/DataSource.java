package com.study.connection;

import java.sql.Connection;

public interface DataSource {
    Connection getConnection();
}
