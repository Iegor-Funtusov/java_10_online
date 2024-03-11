package ua.com.alevel;

import ua.com.alevel.crud.EntityManager;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

public class CrudOrmMain {
    public static void main(String[] args) {
        AlinaStarter.start(CrudOrmMain.class);

//        Department department = new Department();
//        department.setName("JAVA");

        EntityManager entityManager = new EntityManager();
//        entityManager.create(department);

        Employee employee = new Employee();
        employee.setFirstName("qq");
        employee.setLastName("qqq");
        employee.setAge(23);
        entityManager.create(employee);
    }
}
