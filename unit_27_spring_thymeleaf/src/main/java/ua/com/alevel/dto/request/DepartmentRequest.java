package ua.com.alevel.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest extends ApiRequest {
    private String name;
}
