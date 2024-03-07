package ua.com.alevel.controller;

import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;

public class EmployeeController {

    private final EmployeeService employeeService = ObjectFactory.getInstance().getService(EmployeeService.class);

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
        System.out.println("If you want create employee please enter 1");
        System.out.println("If you want show all employees please enter 2");
        System.out.println("If you want show employee by id please enter 3");
        System.out.println("If you want delete by id please enter 4");
        System.out.println("If you want update by id please enter 5");
        System.out.println("If you want attach employee to department please enter 6");
        System.out.println("If you want detach employee to department please enter 7");
        System.out.println("If you want find all employees by department please enter 8");
        System.out.println("If you want exit please enter 9");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> update(reader);
            case "6" -> attachEmployeeToDepartment(reader);
            case "7" -> detachEmployeeToDepartment(reader);
            case "8" -> findAllEmployeesByDepartment(reader);
            case "9" -> throw new RuntimeException("exit");
        }
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter age");
        String ageString = reader.readLine();
        int age = Integer.parseInt(ageString);
        Employee employee = new Employee();
        employee.setId(Long.parseLong(idString));
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        employeeService.update(employee);
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter age");
        String ageString = reader.readLine();
        int age = Integer.parseInt(ageString);
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAge(age);
        employeeService.create(employee);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        Employee employee = employeeService.findById(Long.parseLong(idString));
        System.out.println("employee = " + employee);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        employeeService.delete(Long.parseLong(idString));
    }

    private void readAll() {
        employeeService.findAll().forEach(System.out::println);
    }

    private void attachEmployeeToDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter department id");
        String departmentIdString = reader.readLine();
        System.out.println("Please enter employee id");
        String employeeIdString = reader.readLine();
        employeeService.attachEmployeeToDepartment(
                Long.parseLong(employeeIdString),
                Long.parseLong(departmentIdString)
        );
    }

    private void detachEmployeeToDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter department id");
        String departmentIdString = reader.readLine();
        System.out.println("Please enter employee id");
        String employeeIdString = reader.readLine();
        employeeService.detachEmployeeToDepartment(
                Long.parseLong(employeeIdString),
                Long.parseLong(departmentIdString)
        );
    }

    private void findAllEmployeesByDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter department id");
        String departmentIdString = reader.readLine();
        employeeService.findAllEmployeesByDepartment(Long.parseLong(departmentIdString))
                .forEach(System.out::println);
    }
}
