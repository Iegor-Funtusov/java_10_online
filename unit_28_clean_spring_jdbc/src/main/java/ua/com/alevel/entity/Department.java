package ua.com.alevel.entity;

public class Department extends BaseEntity {

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
