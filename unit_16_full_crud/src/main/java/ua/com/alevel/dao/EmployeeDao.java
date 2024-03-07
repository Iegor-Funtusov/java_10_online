package ua.com.alevel.dao;

import ua.com.alevel.entity.Employee;

import java.util.Collection;

public interface EmployeeDao extends CrudDao<Employee> {

    void attachEmployeeToDepartment(Long employeeId, Long departmentId);
    void detachEmployeeToDepartment(Long employeeId, Long departmentId);
    Collection<Employee> findAllEmployeesByDepartment(Long departmentId);
    Collection<Employee> findAllEmployeesByNotDepartment(Long departmentId);
}
