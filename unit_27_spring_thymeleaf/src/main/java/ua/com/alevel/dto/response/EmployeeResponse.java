package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse extends ApiResponse {

    private String firstName;
    private String lastName;
    private Integer age;
    private int countOfDepartments;

    public EmployeeResponse(Employee employee) {
        setId(employee.getId());
        setFirstName(employee.getFirstName());
        setLastName(employee.getLastName());
        setAge(employee.getAge());
        if (CollectionUtils.isNotEmpty(employee.getDepartments())) {
            setCountOfDepartments(employee.getDepartments().size());
        }
    }
}
