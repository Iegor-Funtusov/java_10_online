package ua.com.alevel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> { }
