package ua.com.alevel.config.impl;

import ua.com.alevel.config.JdbcService;
import ua.com.alevel.util.ResourceUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class MysqlJdbcService implements JdbcService {

    private Connection connection;

    public MysqlJdbcService() {
        final Map<String, String> map = ResourceUtil.getResources(this.getClass().getClassLoader());
        try {
            final String driver = map.get("jdbc.driver");
            Class.forName(driver);
            this.connection = DriverManager.getConnection(
                    map.get("jdbc.url"),
                    map.get("jdbc.username"),
                    map.get("jdbc.password")
            );
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
