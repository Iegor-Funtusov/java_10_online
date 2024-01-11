package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ListStudent implements StudentStorage {

    private final List<Student> students = new ArrayList<>();

    public void create(Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i) == null) {
                student.setId(i);
                break;
            }
        }
        students.add(student);
    }

    public void update(Student student) {
        for (Student student1 : students) {
            if (student.getId() == student1.getId()) {
                student1 = student;
            }
        }
    }

    public void delete(int id) {
        students.removeIf(s -> s.getId() == id);
    }

    public Student[] findAll() {
        return (Student[]) students.toArray();
    }

    public Student findById(int id) {
        return students.stream().filter(s -> s.getId() == id).findAny().get();
    }
}
