package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alevel.entity.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.firstName like :like")
    List<Employee> find(@Param("like") String like);

    List<Employee> findByFirstNameStartingWith(String name);
    long countByFirstNameStartingWithAndAgeBetween(String name, int start, int end);
    boolean existsByFirstNameEndingWithIgnoreCaseOrLastNameContainingIgnoreCase(String fN, String ln);
//    void deleteBy;
    List<Employee> findByDepartmentsIdIn(Collection<Long> longs);
}
