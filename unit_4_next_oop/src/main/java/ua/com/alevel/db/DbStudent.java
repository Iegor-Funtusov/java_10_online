package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

public class DbStudent implements StudentStorage {

    private Student[] students = new Student[10];

    public void create(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                student.setId(i);
                break;
            }
        }
    }

    public void update(Student student) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == student.getId()) {
                students[i] = student;
                break;
            }
        }
    }

    public void delete(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                students[i] = null;
            }
        }
    }

    public Student[] findAll() {
        return students;
    }

    public Student findById(int id) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null && students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }
}
