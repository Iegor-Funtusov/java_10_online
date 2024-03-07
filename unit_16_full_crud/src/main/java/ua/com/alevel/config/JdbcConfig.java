package ua.com.alevel.config;

import ua.com.alevel.util.ResourceUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class JdbcConfig {

    private Connection connection;

    public JdbcConfig() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_10_online",
                    "root",
                    "Test123!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        final Map<String, String> map = ResourceUtil.getResources(this.getClass().getClassLoader());
//        System.out.println("map = " + map);
//        try {
//            final String driver = map.get("jdbc.driver");
//            Class.forName(driver);
//            this.connection = DriverManager.getConnection(
//                    map.get("jdbc.url"),
//                    map.get("jdbc.username"),
//                    map.get("jdbc.password")
//            );
//        } catch (SQLException e) {
//            System.out.println("SQLException = " + e.getMessage());
//        } catch (ClassNotFoundException e) {
//            System.out.println("ClassNotFoundException = " + e.getMessage());
//        }
    }

    public Connection getConnection() {
        return connection;
    }
}
