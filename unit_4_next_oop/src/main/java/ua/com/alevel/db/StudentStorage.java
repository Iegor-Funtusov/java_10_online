package ua.com.alevel.db;

import ua.com.alevel.entity.Student;

public interface StudentStorage {

    public abstract void create(Student student);
    public abstract void update(Student student);
    public abstract void delete(int id);
    public abstract Student findById(int id);
    public abstract Student[] findAll();
}
