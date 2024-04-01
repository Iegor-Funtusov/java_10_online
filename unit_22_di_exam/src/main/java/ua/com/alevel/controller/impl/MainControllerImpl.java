package ua.com.alevel.controller.impl;

import ua.com.alevel.annotations.*;
import ua.com.alevel.controller.DepartmentController;
import ua.com.alevel.controller.EmployeeController;
import ua.com.alevel.controller.MainController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@BeanClass
@BeanStarter
public class MainControllerImpl implements MainController {

    @InjectBean
    private EmployeeController employeeController;

    @InjectBean
    private DepartmentController departmentController;

    @Main
    public void start() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            menu();
            String position = "";
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                menu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want observe department please enter 1");
        System.out.println("If you want observe employee please enter 2");
        System.out.println("If you want exit please enter 3");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> this.departmentController.start(reader);
            case "2" -> this.employeeController.start(reader);
            case "3" -> System.exit(0);
        }
    }
}
