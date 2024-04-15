package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.alevel.entity.Employee;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse extends ApiResponse {

    private String firstName;
    private String lastName;
    private Integer age;

    public EmployeeResponse(Employee employee) {
        setId(employee.getId());
        setFirstName(employee.getFirstName());
        setLastName(employee.getLastName());
        setAge(employee.getAge());
    }
}
