package ua.com.alevel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class DepartmentStatistics {
    private Long id;
    private String name;
    private Long countOfEmployee;

    public DepartmentStatistics(Long id, String name, Long countOfEmployee) {
        this.id = id;
        this.name = name;
        this.countOfEmployee = countOfEmployee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCountOfEmployee() {
        return countOfEmployee;
    }

    public void setCountOfEmployee(Long countOfEmployee) {
        this.countOfEmployee = countOfEmployee;
    }

    @Override
    public String toString() {
        return "DepartmentStatistics{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countOfEmployee=" + countOfEmployee +
                '}';
    }
}
