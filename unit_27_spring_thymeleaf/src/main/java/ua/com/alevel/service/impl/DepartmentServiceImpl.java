package ua.com.alevel.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dto.DataTableRequest;
import ua.com.alevel.dto.DepartmentStatistics;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.repository.DepartmentRepository;
import ua.com.alevel.repository.EmployeeRepository;
import ua.com.alevel.service.DepartmentService;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

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
    @Transactional(readOnly = true)
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Override
    @Transactional(readOnly = true)
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

    @Override
    public List<Employee> findByNonAttachTodepartment(Long id) {
        return employeeRepository.findByNonAttachToDepartment(id);
    }

    @Override
    public void attachEmployeesToDepartment(Long departmentId, List<Long> employeeIds) {
        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new EntityNotFoundException("not found"));
        List<Employee> employees = employeeRepository.findAllByIdIn(employeeIds);
        Set<Employee> employeesSet = department.getEmployees();
        Set<Employee> attachedEmployees = new HashSet<>(employees);
        employeesSet.addAll(attachedEmployees);
        departmentRepository.save(department);
    }
}
