package ua.com.alevel.service.impl;

import jakarta.persistence.EntityNotFoundException;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.dao.impl.DepartmentDaoImpl;
import ua.com.alevel.entity.Department;
import ua.com.alevel.service.DepartmentService;

import java.util.Collection;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    @Override
    public void create(Department entity) {
        departmentDao.create(entity);
    }

    @Override
    public void update(Department entity) {
        departmentDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        departmentDao.delete(id);
    }

    @Override
    public Department findById(Long id) {
        return departmentDao.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Override
    public Collection<Department> findAll() {
        return departmentDao.findAll();
    }
}
