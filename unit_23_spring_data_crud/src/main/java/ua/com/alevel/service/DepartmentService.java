package ua.com.alevel.service;

import ua.com.alevel.dto.DepartmentStatistics;
import ua.com.alevel.entity.Department;

import java.util.List;

public interface DepartmentService extends CrudService<Department> {
    List<DepartmentStatistics> findDepartmentsStatistics();
}
