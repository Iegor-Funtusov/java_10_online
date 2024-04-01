package ua.com.alevel.config;

import org.reflections.Reflections;
import ua.com.alevel.annotations.InitMethod;
import ua.com.alevel.annotations.InjectBean;
import ua.com.alevel.annotations.Value;
import ua.com.alevel.util.ResourceUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public class AppConfig {

    private final Reflections scanner;
    private final Class<?> mainClass;

    public AppConfig(Class<?> mainClass) {
        this.scanner = new Reflections(mainClass.getPackageName());
        this.mainClass = mainClass;
    }

    public void configureBeans(final Map<Class<?>, Object> beanMap) {
        beanMap.values().forEach(bean -> {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectBean.class)) {
                    Class<?> fieldType = field.getType();
                    Object injectedBean = beanMap.get(fieldType);
                    if (injectedBean == null) {
                        throw new RuntimeException(field.getName() + " hasn't implementation");
                    }
                    field.setAccessible(true);
                    try {
                        field.set(bean, injectedBean);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void configureBeans(Collection<Object> beans) {
        beans.forEach(bean -> {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Value.class)) {
                    Value valueAnnotation = field.getAnnotation(Value.class);
                    String filedValue = valueAnnotation.filedValue();
                    final Map<String, String> map = ResourceUtil.getResources(this.getClass().getClassLoader());
                    final String value = map.get(filedValue);
                    field.setAccessible(true);
                    try {
                        field.set(bean, value);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void initPostConstructLogic(Collection<Object> beans) {
        beans.forEach(bean -> {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(InitMethod.class)) {
                System.out.println("method = " + method);
                    try {
                        method.setAccessible(true);
                        method.invoke(bean);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public Reflections getScanner() {
        return scanner;
    }
}
