package ua.com.alevel.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class ResourceUtil {

    private ResourceUtil() {}

    public static Map<String, String> getResources(ClassLoader classLoader) {
        try(InputStream inputStream = classLoader.getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Map<String, String> propertyMap = new HashMap<>();
            properties.forEach((key, value) ->
                    propertyMap.put(String.valueOf(key), String.valueOf(value)));
            return propertyMap;
        } catch (IOException e) {
            throw new RuntimeException("file not found");
        }
    }
}
