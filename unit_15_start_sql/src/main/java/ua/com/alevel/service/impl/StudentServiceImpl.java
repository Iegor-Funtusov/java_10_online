package ua.com.alevel.service.impl;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.entity.Student;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.service.StudentService;

import java.util.Collection;

public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao = new StudentDaoImpl();

    @Override
    public void create(Student entity) {
        studentDao.create(entity);
    }

    @Override
    public void update(Student entity) {
        checkIfExists(entity.getId());
        studentDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        checkIfExists(id);
        studentDao.delete(id);
    }

    @Override
    public Student findById(Long id) {
        return studentDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    @Override
    public Collection<Student> findAll() {
        return studentDao.findAll();
    }

    private void checkIfExists(Long id) {
        if (!studentDao.existById(id)) {
            throw new EntityNotFoundException("Student not found");
        }
    }
}
