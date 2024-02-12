package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public final class StudentDB {

    private static StudentDB instance;
    private final List<Student> students = new ArrayList<>();

    private StudentDB() {}

    public static StudentDB getInstance() {
        if (instance == null) {
            instance = new StudentDB();
        }
        return instance;
    }

    public void add(Student student) {
        student.setId(generateId());
        students.add(student);
    }

    public void delete(String id) {
        students.removeIf(s -> s.getId().equals(id));
    }

    public Optional<Student> findById(String id) {
        return students
                .stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    public List<Student> findAll() {
        return students;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (students.stream().anyMatch(s -> s.getId().equals(id))) {
            return generateId();
        }
        return id;
    }
}
