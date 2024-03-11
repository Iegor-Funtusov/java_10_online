package ua.com.alevel.factory;

import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.Store;
import ua.com.alevel.annotation.Column;
import ua.com.alevel.annotation.Entity;
import ua.com.alevel.annotation.Id;
import ua.com.alevel.util.TableUtil;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.reflections.scanners.Scanners.TypesAnnotated;

public class EntityFactory {

    private final Reflections scanner;
    private final Store store;
    private Set<String> entityClassNames = new HashSet<>();
    private final List<Class<?>> entities = new ArrayList<>();
    private final List<String> createTableQueries = new ArrayList<>();

    public EntityFactory(Class<?> mainClass) {
        this.scanner = new Reflections(mainClass.getPackageName());
        this.store = this.scanner.getStore();
        this.collectEntityClassNames();
        this.buildEntityClasses();
        this.buildEntityTables();
    }

    public List<String> getCreateTableQueries() {
        return createTableQueries;
    }

    private void collectEntityClassNames() {
        this.store.forEach((k,v) -> {
            if (k.equals(TypesAnnotated.name())) {
                v.forEach((key, value) -> {
                    if (key.equals(Entity.class.getName())) {
                        this.entityClassNames = value;
                    }
                });
            }
        });
    }

    private void buildEntityClasses() {
        this.entityClassNames.forEach(entity -> {
            try {
                Class<?> entityClass = Class.forName(entity);
                this.entities.add(entityClass);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void buildEntityTables() {
        this.entities.forEach(this::buildTable);
    }

    private void buildTable(Class<?> entityClass) {
        StringBuilder builder = new StringBuilder();
        builder.append("create table ");
        String tableName = TableUtil.getTableName(entityClass);
        builder.append(tableName);
        builder.append(" ");
        builder.append("(");
        Class<?> superClass = entityClass.getSuperclass();
        if (superClass != null) {
            Field[] fields = superClass.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    if (field.isAnnotationPresent(Id.class)) {
                        String columnName = getColumnName(field);
                        builder.append(columnName);
                        builder.append(" bigint auto_increment primary key not null, ");
                    }
                }
            }
        }

        Field[] fields = entityClass.getDeclaredFields();

        for (Field field : fields) {
            String columnName = getColumnName(field);
            builder.append(columnName);

            if (field.getType().isAssignableFrom(String.class)) {
                builder.append(" ");
                builder.append("varchar(255) null, ");
            }

            if (field.getType().isAssignableFrom(Integer.class)) {
                builder.append(" ");
                builder.append("int null, ");
            }
        }

        builder.append(");");

        String query = builder.toString();
        query = query.replaceAll(", \\)", ")");
        this.createTableQueries.add(query);
    }

    private String getColumnName(Field field) {
        Column column = field.getAnnotation(Column.class);
        String columnName = column.name();
        if (StringUtils.isNotBlank(columnName)) {
            return columnName;
        }
        return field.getName();
    }
}

//create table employees
//        (
//                id bigint auto_increment primary key not null,
//                first_name varchar(255) null,
//last_name varchar(255) null,
//age int null
//        );