package ua.com.alevel.entity;

import ua.com.alevel.annotation.Column;
import ua.com.alevel.annotation.Entity;

@Entity(name = "departments")
public class Department extends BaseEntity {

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
