package ua.com.alevel.factory;

import org.apache.commons.lang3.ArrayUtils;
import org.reflections.Store;
import org.reflections.scanners.Scanners;
import ua.com.alevel.annotations.BeanClass;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {

    private final Store store;
    private final Map<Class<?>, Object> beanMap = new HashMap<>();

    public BeanFactory(Store store) {
        this.store = store;
    }

    public void initBeans() {
        this.store.forEach((k, v) -> {
            if (k.equals(Scanners.TypesAnnotated.name())) {
                v.forEach((key, value) -> {
                    if (key.equals(BeanClass.class.getName())) {
                        value.forEach(beanName -> {
                            try {
                                Class<?> beanClass = Class.forName(beanName);
                                Class<?>[] interfaces = beanClass.getInterfaces();
                                if (ArrayUtils.isEmpty(interfaces)) {
                                    throw new RuntimeException("Bean class " + beanName + " hasn't interfaces");
                                }
                                Object bean = beanClass.getDeclaredConstructor().newInstance();
                                this.beanMap.put(interfaces[0], bean);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            } catch (InvocationTargetException e) {
                                throw new RuntimeException(e);
                            } catch (InstantiationException e) {
                                throw new RuntimeException(e);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            } catch (NoSuchMethodException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                });
            }
        });
    }

    public Map<Class<?>, Object> getBeanMap() {
        return beanMap;
    }
}
