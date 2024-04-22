package ua.com.alevel.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.alevel.StartApp;

public class AppConfig {

    private static final String STRING_PATH = "spring/";
    private static final String[] STRING_RESOURCES = new String[] {
            STRING_PATH + "app-context.xml",
            STRING_PATH + "startapp.xml",
            STRING_PATH + "jdbc.xml",
            STRING_PATH + "controllers.xml",
            STRING_PATH + "services.xml",
            STRING_PATH + "daos.xml",
    };

    public static void start() {
        ApplicationContext context = new ClassPathXmlApplicationContext(STRING_RESOURCES);
        StartApp startApp = context.getBean(StartApp.class);
        startApp.run();
    }
}
