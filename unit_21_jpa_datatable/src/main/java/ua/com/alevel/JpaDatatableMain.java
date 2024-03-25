package ua.com.alevel;

import ua.com.alevel.config.ObjectFactory;

public class JpaDatatableMain {

    public static void main(String[] args) {
        ObjectFactory factory = ObjectFactory.getInstance();
        factory.initObjectFactory();
        StartApp startApp = new StartApp();
        startApp.run();
    }
}
