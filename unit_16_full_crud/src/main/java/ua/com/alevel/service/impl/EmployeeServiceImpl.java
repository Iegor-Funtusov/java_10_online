package ua.com.alevel.service.impl;

import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.service.EmployeeService;

import java.util.Collection;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = ObjectFactory.getInstance().getService(EmployeeDao.class);

    @Override
    public void create(Employee entity) {
        employeeDao.create(entity);
    }

    @Override
    public void update(Employee entity) {
        employeeDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public void attachEmployeeToDepartment(Long employeeId, Long departmentId) {
        employeeDao.attachEmployeeToDepartment(employeeId, departmentId);
    }

    @Override
    public void detachEmployeeToDepartment(Long employeeId, Long departmentId) {
        employeeDao.detachEmployeeToDepartment(employeeId, departmentId);
    }

    @Override
    public Collection<Employee> findAllEmployeesByDepartment(Long departmentId) {
        return employeeDao.findAllEmployeesByDepartment(departmentId);
    }

    @Override
    public Collection<Employee> findAllEmployeesByNotDepartment(Long departmentId) {
        return employeeDao.findAllEmployeesByNotDepartment(departmentId);
    }
}
