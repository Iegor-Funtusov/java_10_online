package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.entity.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @Query("select e from Employee e where e.id not in (select e from Employee e join e.departments as d where d.id = :departmentId)")
    @Query(value = "select id, first_name, last_name, age from employees where id not in (select id from employees left join dep_emp de on de.emp_id = employees.id where de.dep_id = ?1)", nativeQuery = true)
    List<Employee> findByNonAttachToDepartment(Long departmentId);

    List<Employee> findAllByIdIn(List<Long> ids);
}
