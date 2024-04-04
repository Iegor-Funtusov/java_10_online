package ua.com.alevel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MainController {

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private DepartmentController departmentController;

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
