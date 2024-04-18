package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.alevel.dto.DepartmentStatistics;
import ua.com.alevel.entity.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select new ua.com.alevel.dto.DepartmentStatistics(d.id, d.name, count(d.id)) from Department d left join d.employees group by d.id")
    List<DepartmentStatistics> findDepartmentsStatistics();
}
