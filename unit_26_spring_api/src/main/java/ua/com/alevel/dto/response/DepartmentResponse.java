package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.entity.Department;

@Getter
@Setter
public class DepartmentResponse extends ApiResponse {
    private String name;

    public DepartmentResponse() {}

    public DepartmentResponse(Department department) {
        setId(department.getId());
        setName(department.getName());
    }
}
