package ua.com.alevel.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.request.DepartmentRequest;
import ua.com.alevel.dto.response.DepartmentResponse;
import ua.com.alevel.entity.Department;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.service.DepartmentService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentService departmentService;

    @Override
    public void create(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        departmentService.create(department);
    }

    @Override
    public void update(DepartmentRequest request, Long id) {
        Department department = departmentService.findById(id);
        department.setName(request.getName());
        departmentService.update(department);
    }

    @Override
    public void delete(Long id) {
        departmentService.delete(id);
    }

    @Override
    public DepartmentResponse findById(Long id) {
        return new DepartmentResponse(departmentService.findById(id));
    }

    @Override
    public Collection<DepartmentResponse> findAll() {
        return departmentService.findAll().stream().map(DepartmentResponse::new).toList();
    }
}
