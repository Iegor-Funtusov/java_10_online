package ua.com.alevel.controller;

import ua.com.alevel.config.ObjectFactory;
import ua.com.alevel.dao.DataTableRequest;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.type.OrderType;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
        System.out.println("If you want find all employees by not department please enter 9");
        System.out.println("If you want find all and sort employees please enter 10");
        System.out.println("If you want exit please enter 11");
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
            case "9" -> findAllEmployeesByNotDepartment(reader);
            case "10" -> findAllAndSortEmployees(reader);
            case "11" -> throw new RuntimeException("exit");
        }
    }

    private void findAllAndSortEmployees(BufferedReader reader) throws IOException {
        DataTableRequest request = new DataTableRequest();
        System.out.println("If you want sort by first name please enter 1");
        System.out.println("If you want sort by last name please enter 2");
        System.out.println("If you want sort by age please enter 3");
        String fieldNumber = reader.readLine();
        switch (fieldNumber) {
            case "1" -> request.setColumn("first_name");
            case "2" -> request.setColumn("last_name");
            case "3" -> request.setColumn("age");
        }
        System.out.println("If you want order sorting by asc please enter 1");
        System.out.println("If you want order sorting by desc please enter 2");
        System.out.println("Please enter type: asc or desc");
        String sort = reader.readLine();
        switch (sort) {
            case "1" -> request.setOrderType(OrderType.ASC);
            case "2" -> request.setOrderType(OrderType.DESC);
        }

        System.out.println("Please enter reviewed page number (from 1 to ...)");
        int page = Integer.parseInt(reader.readLine());
        System.out.println("Please enter reviewed page size (from 1 to ...)");
        int size = Integer.parseInt(reader.readLine());
        request.setPage(page);
        request.setSize(size);

        List<Employee> employees = (List<Employee>) employeeService.findAll(request);
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            System.out.println("#: " + (i + 1) +
                    ", Id: " + employee.getId() +
                    ", First name: " + employee.getFirstName() +
                    ", Last name: " + employee.getLastName() +
                    ", Age: " + employee.getAge());
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
        Collection<Employee> employees = employeeService.findAll();
        for (Employee employee : employees) {
            System.out.println("employee = " + employee);
            Set<Department> departments = employee.getDepartments();
//            if (CollectionUtils.isNotEmpty(departments)) { }
        }
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

    private void findAllEmployeesByNotDepartment(BufferedReader reader) throws IOException {
        System.out.println("Please enter department id");
        String departmentIdString = reader.readLine();
        employeeService.findAllEmployeesByNotDepartment(Long.parseLong(departmentIdString))
                .forEach(System.out::println);
    }
}
