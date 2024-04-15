package ua.com.alevel.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest extends ApiRequest {

    private String firstName;
    private String lastName;
    private Integer age;
}
