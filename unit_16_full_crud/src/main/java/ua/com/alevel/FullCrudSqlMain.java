package ua.com.alevel;

import ua.com.alevel.config.JdbcConfig;
import ua.com.alevel.config.JdbcService;
import ua.com.alevel.config.ObjectFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FullCrudSqlMain {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java_10_online",
                    "root",
                    "Test123!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        JdbcConfig jdbcConfig = new JdbcConfig();
//        ObjectFactory factory = ObjectFactory.getInstance();
//        factory.initObjectFactory();
//        JdbcService jdbcService = ObjectFactory.getInstance().getService(JdbcService.class);
//        jdbcService.setConnection(jdbcConfig.getConnection());
//        StartApp startApp = new StartApp();
//        startApp.run();
    }
}
