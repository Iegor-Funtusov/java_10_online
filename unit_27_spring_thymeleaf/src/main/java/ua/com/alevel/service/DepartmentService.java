package ua.com.alevel.service;

import ua.com.alevel.dto.DepartmentStatistics;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import java.util.List;

public interface DepartmentService extends CrudService<Department> {
    List<DepartmentStatistics> findDepartmentsStatistics();
    List<Employee> findByNonAttachTodepartment(Long id);
    void attachEmployeesToDepartment(Long departmentId, List<Long> employeeIds);
}
