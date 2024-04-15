package ua.com.alevel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentStatistics {
    private Long id;
    private String name;
    private Long countOfEmployee;
}
