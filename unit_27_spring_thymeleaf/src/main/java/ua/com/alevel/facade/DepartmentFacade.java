package ua.com.alevel.facade;

import ua.com.alevel.dto.request.DepartmentRequest;
import ua.com.alevel.dto.response.DepartmentResponse;
import ua.com.alevel.dto.response.EmployeeResponse;

import java.util.List;

public interface DepartmentFacade extends CrudFacade<DepartmentRequest, DepartmentResponse> {

    List<EmployeeResponse> findByNonAttachToDepartment(Long departmentId);
    void attachEmployeesToDepartment(Long departmentId, List<Long> employeeIds);
}
