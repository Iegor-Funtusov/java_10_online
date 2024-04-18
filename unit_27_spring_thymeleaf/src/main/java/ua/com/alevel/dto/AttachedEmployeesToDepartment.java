package ua.com.alevel.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttachedEmployeesToDepartment {
    List<Long> employeesId;
}
