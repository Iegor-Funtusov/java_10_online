package ua.com.alevel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.dto.DepartmentStatistics;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "departments")
@SqlResultSetMapping(
        name = "DepartmentStatisticsMapping",
        classes = @ConstructorResult(
                targetClass = DepartmentStatistics.class,
                columns = { @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "employee_count", type = Long.class)}))
public class Department extends BaseEntity {

    private String name;

    @ManyToMany
    @JoinTable(
            name = "dep_emp",
            joinColumns = @JoinColumn(name = "dep_id"),
            inverseJoinColumns = @JoinColumn(name = "emp_id")
    )
    @ToString.Exclude
    private Set<Employee> employees;

    public Department() {
        this.employees = new HashSet<>();
    }
}
