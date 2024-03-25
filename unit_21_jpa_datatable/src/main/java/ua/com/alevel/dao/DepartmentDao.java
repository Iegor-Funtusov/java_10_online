package ua.com.alevel.dao;

import ua.com.alevel.dto.DepartmentStatistics;
import ua.com.alevel.entity.Department;

import java.util.List;

public interface DepartmentDao extends CrudDao<Department> {

    List<DepartmentStatistics> findDepartmentsByNativeQuery();
    List<DepartmentStatistics> findDepartmentsByTypedQuery();
}
