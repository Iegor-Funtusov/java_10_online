package ua.com.alevel.service;

import ua.com.alevel.db.DbStudent;
import ua.com.alevel.entity.Student;

// Service class
public class StudentService {

    private DbStudent dbStudent = new DbStudent();

    public void create(Student student) {
        if (student.getFirstName() != null && student.getLastName() != null && student.getAge() > 0) {
            dbStudent.create(student);
        }
    }

    public void update(Student student) {
        Student current = dbStudent.findById(student.getId());
        if (current != null) {
            dbStudent.update(student);
        }
    }

    public void delete(int id) {
        Student current = dbStudent.findById(id);
        if (current != null) {
            dbStudent.delete(id);
        }
    }

    public Student[] findAll() {
        return dbStudent.findAll();
    }

    public Student findById(int id) {
        return dbStudent.findById(id);
    }
}
