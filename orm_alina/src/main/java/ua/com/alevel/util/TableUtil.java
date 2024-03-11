package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;
import ua.com.alevel.annotation.Entity;

public final class TableUtil {

    private TableUtil() { }

    public static String getTableName(Class<?> entityClass) {
        Entity entityAnnotation = entityClass.getAnnotation(Entity.class);
        String tableName = entityAnnotation.name();
        if (StringUtils.isNotBlank(tableName)) {
            return tableName;
        }
        return entityClass.getSimpleName().toLowerCase();
    }
}
