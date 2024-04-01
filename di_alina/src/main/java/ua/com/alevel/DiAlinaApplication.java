package ua.com.alevel;

import ua.com.alevel.config.AppConfig;
import ua.com.alevel.config.AppStarter;
import ua.com.alevel.factory.BeanFactory;

public class DiAlinaApplication {

    public static void start(Class<?> mainClass) {
        AppConfig appConfig = new AppConfig(mainClass);
        BeanFactory beanFactory = new BeanFactory(appConfig.getScanner().getStore());
        beanFactory.initBeans();
        appConfig.configureBeans(beanFactory.getBeanMap());
        appConfig.configureBeans(beanFactory.getBeanMap().values());
        appConfig.initPostConstructLogic(beanFactory.getBeanMap().values());
        AppStarter appStarter = new AppStarter();
        appStarter.start(beanFactory.getBeanMap());
    }
}
