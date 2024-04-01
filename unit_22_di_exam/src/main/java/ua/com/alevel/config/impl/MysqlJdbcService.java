package ua.com.alevel.config.impl;

import ua.com.alevel.annotations.BeanClass;
import ua.com.alevel.annotations.InitMethod;
import ua.com.alevel.annotations.Value;
import ua.com.alevel.config.JdbcService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@BeanClass
public class MysqlJdbcService implements JdbcService {

    private Connection connection;

    @Value(filedValue = "jdbc.driver")
    private String driver;
    @Value(filedValue = "jdbc.url")
    private String url;
    @Value(filedValue = "jdbc.username")
    private String username;
    @Value(filedValue = "jdbc.password")
    private String password;

    @InitMethod
    private void init() {
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
