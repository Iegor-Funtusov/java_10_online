package ua.com.alevel.service;

import ua.com.alevel.db.StudentGroupRelation;
import ua.com.alevel.entity.Student;

import java.util.List;

public class StudentService {

    private final StudentGroupRelation relation = StudentGroupRelation.getInstance();

    public void create(Student student) {
        relation.createStudent(student);
    }

    public List<Student> allStudents() {
        return relation.allStudents();
    }

    public List<Student> allStudentsByGroup(String groupId) {
        return relation.findAllStudentsByGroup(groupId);
    }
}
