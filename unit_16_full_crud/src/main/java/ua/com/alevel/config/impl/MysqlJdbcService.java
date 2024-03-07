package ua.com.alevel.config.impl;

import ua.com.alevel.config.JdbcService;

import java.sql.Connection;

public class MysqlJdbcService implements JdbcService {

    private Connection connection;

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
