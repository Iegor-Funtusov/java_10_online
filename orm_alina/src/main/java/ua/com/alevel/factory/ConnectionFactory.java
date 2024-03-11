package ua.com.alevel.factory;

import ua.com.alevel.util.ResourceUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionFactory {

    private static final ConnectionFactory instance = new ConnectionFactory();
    private Connection connection;

    private ConnectionFactory() { }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public void initConnection(Class<?> mainClass) {
        ClassLoader classLoader = mainClass.getClassLoader();
        final Map<String, String> map = ResourceUtil.getResources(classLoader);
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

    public Connection getConnection() {
        return connection;
    }
}
