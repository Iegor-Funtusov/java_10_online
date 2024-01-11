package ua.com.alevel.service;

import ua.com.alevel.db.StudentStorage;
import ua.com.alevel.entity.Student;
import ua.com.alevel.factory.StudentStorageFactory;

// Service class
public class StudentService {

    StudentStorage studentStorage = StudentStorageFactory.getStudentStorage();

    public void create(Student student) {
        if (student.getFirstName() != null && student.getLastName() != null && student.getAge() > 0) {
            studentStorage.create(student);
        }
    }

    public void update(Student student) {
        Student current = studentStorage.findById(student.getId());
        if (current != null) {
            studentStorage.update(student);
        }
    }

    public void delete(int id) {
        Student current = studentStorage.findById(id);
        if (current != null) {
            studentStorage.delete(id);
        }
    }

    public Student[] findAll() {
        return studentStorage.findAll();
    }

    public Student findById(int id) {
        return studentStorage.findById(id);
    }
}
