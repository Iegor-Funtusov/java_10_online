package ua.com.alevel.controller;

import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.DepartmentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

public class DepartmentController {

    private final DepartmentService departmentService = ObjectFactory.getInstance().getService(DepartmentService.class);

    public void start(BufferedReader reader) throws IOException {
        menu();
        String position = "";
        while ((position = reader.readLine()) != null) {
            crud(position, reader);
            menu();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create department please enter 1");
        System.out.println("If you want show all departments please enter 2");
        System.out.println("If you want show department by id please enter 3");
        System.out.println("If you want delete by id please enter 4");
        System.out.println("If you want update by id please enter 5");
        System.out.println("If you want exit please enter 6");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> update(reader);
            case "6" -> throw new RuntimeException("exit");
        }
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        System.out.println("Please enter name");
        String name = reader.readLine();
        Department department = new Department();
        department.setId(Long.parseLong(idString));
        department.setName(name);
        departmentService.update(department);
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter name");
        String name = reader.readLine();
        Department department = new Department();
        department.setName(name);
        departmentService.create(department);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        Department department = departmentService.findById(Long.parseLong(idString));
        System.out.println("department = " + department);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        departmentService.delete(Long.parseLong(idString));
    }

    private void readAll() {
        Collection<Department> departments = departmentService.findAll();
        for (Department department : departments) {
            System.out.println("department = " + department);
            Set<Employee> employees = department.getEmployees();
            // AOP -> aspect oriented programming (AspectJ)
            if (CollectionUtils.isNotEmpty(employees)) {
                for (Employee employee : employees) {
                    System.out.println("employee = " + employee);
                }
            }
        }
    }
}
