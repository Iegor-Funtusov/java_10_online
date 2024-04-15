package ua.com.alevel.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.DataTableRequest;
import ua.com.alevel.dto.OrderType;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.repository.EmployeeRepository;
import ua.com.alevel.service.EmployeeService;

import java.util.Collection;
import java.util.Collections;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void create(Employee entity) {
        employeeRepository.save(entity);
    }

    @Override
    public void update(Employee entity) {
        employeeRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found"));
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Collection<Employee> findAll(DataTableRequest request) {

        int page = request.getPage() - 1;
        int size = request.getSize();
        String column = request.getColumn();
        OrderType orderType = request.getOrderType();

        Sort sort = OrderType.ASC.equals(orderType) ?
                Sort.by(column).ascending() :
                Sort.by(column).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return employeeRepository.findAll(pageable).getContent();
    }

    @Override
    public void attachEmployeeToDepartment(Long employeeId, Long departmentId) {
//        employeeDao.attachEmployeeToDepartment(employeeId, departmentId);
    }

    @Override
    public void detachEmployeeToDepartment(Long employeeId, Long departmentId) {
//        employeeDao.detachEmployeeToDepartment(employeeId, departmentId);
    }

    @Override
    public Collection<Employee> findAllEmployeesByDepartment(Long departmentId) {
        return Collections.emptyList();
//        return employeeDao.findAllEmployeesByDepartment(departmentId);
    }

    @Override
    public Collection<Employee> findAllEmployeesByNotDepartment(Long departmentId) {
        return Collections.emptyList();
//        return employeeDao.findAllEmployeesByNotDepartment(departmentId);
    }
}
