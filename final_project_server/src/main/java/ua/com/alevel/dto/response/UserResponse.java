package ua.com.alevel.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends ApiResponse<Long> {

    private String firstName;
    private String lastName;
    private Integer age;
}
