package ua.com.alevel.config;

import javafx.fxml.FXMLLoader;

import java.util.HashMap;
import java.util.Map;

public class LoaderFactory {

    private final Map<String, FXMLLoader> loaderPageMap = new HashMap<>();

    public LoaderFactory(Class<?> mainClass) {
        final Map<String, String> loaderMap = Map.ofEntries(
                Map.entry(LoaderPage.LAYOUT.getView(), LoaderPage.LAYOUT.getPage()),
                Map.entry(LoaderPage.STUDENTS.getView(), LoaderPage.STUDENTS.getPage()),
                Map.entry(LoaderPage.GROUPS.getView(), LoaderPage.GROUPS.getPage())
        );
        loaderMap.forEach((k, v) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainClass.getResource(v));
            loaderPageMap.put(k, loader);
        });
    }

    public Map<String, FXMLLoader> getLoaderPageMap() {
        return loaderPageMap;
    }
}
