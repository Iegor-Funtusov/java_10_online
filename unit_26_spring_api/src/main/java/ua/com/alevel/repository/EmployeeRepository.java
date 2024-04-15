package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> { }
