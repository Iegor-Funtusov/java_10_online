package ua.com.alevel.service;

import ua.com.alevel.entity.Employee;

import java.util.Collection;

public interface EmployeeService extends CrudService<Employee> {

    void attachEmployeeToDepartment(Long employeeId, Long departmentId);
    void detachEmployeeToDepartment(Long employeeId, Long departmentId);
    Collection<Employee> findAllEmployeesByDepartment(Long departmentId);
    Collection<Employee> findAllEmployeesByNotDepartment(Long departmentId);
}
