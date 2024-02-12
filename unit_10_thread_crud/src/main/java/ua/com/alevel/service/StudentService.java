package ua.com.alevel.service;

import ua.com.alevel.db.StudentDB;
import ua.com.alevel.entity.Student;
import ua.com.alevel.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private final StudentDB studentDB = StudentDB.getInstance();

    public void create(Student student) {
        studentDB.add(student);
    }

    public void delete(String id) {
        studentDB.delete(id);
    }

    public Student findById(String id) {
        Optional<Student> studentOptional = studentDB.findById(id);
        if (studentOptional.isEmpty()) {
            throw new EntityNotFoundException("Student not found by id: " + id);
        }
        return studentOptional.get();
    }

    public List<Student> findAll() {
        return studentDB.findAll();
    }
}
