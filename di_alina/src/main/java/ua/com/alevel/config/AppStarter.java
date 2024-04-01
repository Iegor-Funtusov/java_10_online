package ua.com.alevel.config;

import ua.com.alevel.annotations.BeanStarter;
import ua.com.alevel.annotations.Main;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class AppStarter {

    public void start(final Map<Class<?>, Object> beanMap) {
        beanMap.values().forEach(bean -> {
            if (bean.getClass().isAnnotationPresent(BeanStarter.class)) {
                Method[] methods = bean.getClass().getDeclaredMethods();
                int sizeMainMethods = Stream.of(methods)
                        .filter(method -> method.isAnnotationPresent(Main.class))
                        .toList()
                        .size();
                if (sizeMainMethods == 0) {
                    throw new RuntimeException("Can't find Main method");
                }

                if (sizeMainMethods > 1) {
                    throw new RuntimeException("Main annotation present more then 1");
                }
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Main.class)) {
                        try {
                            method.invoke(bean);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
    }
}
