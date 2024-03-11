package ua.com.alevel.crud;

import ua.com.alevel.annotation.Column;
import ua.com.alevel.factory.ConnectionFactory;
import ua.com.alevel.util.TableUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntityManager {

    private final Connection connection = ConnectionFactory.getInstance().getConnection();

    public void create(Object entity) {
        Class<?> entityClass = entity.getClass();

        StringBuilder builder = new StringBuilder();
        builder.append("insert into ");
        String tableName = TableUtil.getTableName(entityClass);
        builder.append(tableName);
        builder.append(" values ");
        builder.append("(");
        builder.append("default, ");

        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                builder.append("?, ");
            }
        }

        builder.append(")");

        String query = builder.toString();
        query = query.replaceAll(", \\)", ");");

        try(PreparedStatement ps = this.connection.prepareStatement(query)) {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].isAnnotationPresent(Column.class)) {
                    fields[i].setAccessible(true);
                    Object columnValue = fields[i].get(entity);
                    if (fields[i].getType().isAssignableFrom(String.class)) {
                        ps.setString(i + 1, (String) columnValue);
                    }
                    if (fields[i].getType().isAssignableFrom(Integer.class)) {
                        ps.setInt(i + 1, (Integer) columnValue);
                    }
                }
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
