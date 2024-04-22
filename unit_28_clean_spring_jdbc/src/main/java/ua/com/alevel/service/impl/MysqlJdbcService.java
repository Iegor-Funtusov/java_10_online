package ua.com.alevel.service.impl;

import ua.com.alevel.service.JdbcService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlJdbcService implements JdbcService {

    private Connection connection;

    public MysqlJdbcService(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("SQLException = " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException = " + e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
