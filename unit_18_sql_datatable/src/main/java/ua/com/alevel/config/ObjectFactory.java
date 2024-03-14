package ua.com.alevel.config;

import ua.com.alevel.config.impl.MysqlJdbcService;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.dao.impl.DepartmentDaoImpl;
import ua.com.alevel.dao.impl.EmployeeDaoImpl;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.service.impl.DepartmentServiceImpl;
import ua.com.alevel.service.impl.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ObjectFactory {

    private static final ObjectFactory instance = new ObjectFactory();
    private final Map<Class<?>, Object> objectMap = new HashMap<>();

    private ObjectFactory() {}

    public static ObjectFactory getInstance() {
        return instance;
    }

    public <S> S getService(Class<S> interfaceService) {
        return (S) objectMap.get(interfaceService);
    }

    public void initObjectFactory() {
        objectMap.put(JdbcService.class, new MysqlJdbcService());
        objectMap.put(EmployeeDao.class, new EmployeeDaoImpl());
        objectMap.put(DepartmentDao.class, new DepartmentDaoImpl());
        objectMap.put(DepartmentService.class, new DepartmentServiceImpl());
        objectMap.put(EmployeeService.class, new EmployeeServiceImpl());
    }
}
