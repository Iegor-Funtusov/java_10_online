package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.db.FileDb;
import ua.com.alevel.entity.Student;
import ua.com.alevel.factory.DBFactory;

import java.util.Collection;
import java.util.Optional;

public class StudentDaoImpl implements StudentDao {

    private final FileDb fileDb = DBFactory.getInstance().getFileDb();

    @Override
    public void create(Student entity) {
        fileDb.create(entity);
    }

    @Override
    public void update(Student entity) {
        fileDb.update(entity);
    }

    @Override
    public void delete(String id) {
        fileDb.delete(id);
    }

    @Override
    public Optional<Student> findById(String id) {
        return fileDb.findById(id);
    }

    @Override
    public Collection<Student> findAll() {
        return fileDb.findAll();
    }
}
