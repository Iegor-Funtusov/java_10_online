package ua.com.alevel.builder;

import ua.com.alevel.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TableBuilder {

    private final Connection connection;

    public TableBuilder(Class<?> mainClass) {
        ConnectionFactory factory = ConnectionFactory.getInstance();
        factory.initConnection(mainClass);
        connection = factory.getConnection();
    }

    public void createTables(List<String> queries) {
        for (String query : queries) {
            try(PreparedStatement ps = connection.prepareStatement(query)) {
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
