package ua.com.alevel.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.DataTableRequest;
import ua.com.alevel.dto.DepartmentStatistics;
import ua.com.alevel.entity.Department;
import ua.com.alevel.repository.DepartmentRepository;
import ua.com.alevel.service.DepartmentService;

import java.util.Collection;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void create(Department entity) {
        departmentRepository.save(entity);
    }

    @Override
    public void update(Department entity) {
        departmentRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Override
    public Collection<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Collection<Department> findAll(DataTableRequest request) {
        return null;
    }

    @Override
    public List<DepartmentStatistics> findDepartmentsStatistics() {
        return departmentRepository.findDepartmentsStatistics();
    }
}
