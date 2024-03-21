package ua.com.alevel;

import ua.com.alevel.config.ObjectFactory;

public class JoinHibernateMain {
    public static void main(String[] args) {
        ObjectFactory factory = ObjectFactory.getInstance();
        factory.initObjectFactory();
        StartApp startApp = new StartApp();
        startApp.run();
    }
}