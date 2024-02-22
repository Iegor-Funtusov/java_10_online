package ua.com.alevel.factory;

import ua.com.alevel.db.FileDb;
import ua.com.alevel.db.impl.CsvFileDb;
import ua.com.alevel.db.impl.JsonFileDb;
import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.util.ResourceUtil;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DBFactory {

    private static DBFactory instance;

    private DBFactory() {}

    public static DBFactory getInstance() {
        if (instance == null) {
            return new DBFactory();
        }
        return instance;
    }

    public FileDb getFileDb() {
        final Map<String, String> pMap = ResourceUtil.getResources(this.getClass().getClassLoader());
        final String dbInstance = pMap.get("db.instance");
        if (dbInstance.equals(ResourceType.CSV.getType())) {
            File file = new File(FileType.STUDENTS_CSV.getType());
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return new CsvFileDb();
        }
        if (dbInstance.equals(ResourceType.JSON.getType())) {
            File file = new File(FileType.STUDENTS_JSON.getType());
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return new JsonFileDb();
        }
        throw new RuntimeException("incorrect db format");
    }
}
