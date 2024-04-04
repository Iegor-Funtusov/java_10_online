package ua.com.alevel;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.com.alevel.controller.MainController;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.repository.EmployeeRepository;

import java.util.Arrays;
import java.util.List;

public class SpringDataMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("ua.com.alevel");
        appContext.refresh();

//        MainController mainController = appContext.getBean(MainController.class);
//        mainController.start();

        EmployeeRepository employeeRepository = appContext.getBean(EmployeeRepository.class);
        List<Employee> employees = employeeRepository.findByFirstNameStartingWith("q");
        System.out.println("employees = " + employees);

        long count = employeeRepository.countByFirstNameStartingWithAndAgeBetween("q", 19, 25);
        System.out.println("count = " + count);

        boolean exists = employeeRepository.existsByFirstNameEndingWithIgnoreCaseOrLastNameContainingIgnoreCase("s", "g");
        System.out.println("exists = " + exists);

        employees = employeeRepository.findByDepartmentsIdIn(List.of(1L));
        System.out.println("employees = " + employees);
        appContext.close();
    }
}