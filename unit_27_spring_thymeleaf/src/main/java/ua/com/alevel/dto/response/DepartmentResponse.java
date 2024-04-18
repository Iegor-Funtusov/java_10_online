package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.entity.Department;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DepartmentResponse extends ApiResponse {
    private String name;
    private int countOfEmployees;
    private List<String> employees = new ArrayList<>();

    public DepartmentResponse() {}

    public DepartmentResponse(Department department) {
        setId(department.getId());
        setName(department.getName());
        if (CollectionUtils.isNotEmpty(department.getEmployees())) {
            setCountOfEmployees(department.getEmployees().size());
            setEmployees(
                    department
                            .getEmployees()
                            .stream()
                            .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                            .toList()
            );
        }
    }
}
