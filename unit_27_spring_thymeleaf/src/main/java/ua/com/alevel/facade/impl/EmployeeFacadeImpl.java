package ua.com.alevel.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.EmployeeRequest;
import ua.com.alevel.dto.response.EmployeeResponse;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.service.EmployeeService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService employeeService;

    @Override
    public void create(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAge(request.getAge());
        employeeService.create(employee);
    }

    @Override
    public void update(EmployeeRequest request, Long id) {
        Employee employee = employeeService.findById(id);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAge(request.getAge());
        employeeService.update(employee);
    }

    @Override
    public void delete(Long id) {
        employeeService.delete(id);
    }

    @Override
    public EmployeeResponse findById(Long id) {
        return new EmployeeResponse(employeeService.findById(id));
    }

    @Override
    public Collection<EmployeeResponse> findAll() {
        return employeeService.findAll().stream().map(EmployeeResponse::new).toList();
    }
}
