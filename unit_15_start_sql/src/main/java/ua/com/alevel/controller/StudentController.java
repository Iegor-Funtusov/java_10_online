package ua.com.alevel.controller;

import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.service.impl.StudentServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentController {

    private final StudentService studentService = new StudentServiceImpl();

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
        System.out.println("If you want create student please enter 1");
        System.out.println("If you want show all students please enter 2");
        System.out.println("If you want show student by id please enter 3");
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
            case "6" -> System.exit(0);
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
        Student student = new Student();
        student.setId(Long.parseLong(idString));
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        studentService.update(student);
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        System.out.println("Please enter age");
        String ageString = reader.readLine();
        int age = Integer.parseInt(ageString);
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        studentService.create(student);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        Student student = studentService.findById(Long.parseLong(idString));
        System.out.println("student = " + student);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        String idString = reader.readLine();
        studentService.delete(Long.parseLong(idString));
    }

    private void readAll() {
        studentService.findAll().forEach(System.out::println);
    }
}
