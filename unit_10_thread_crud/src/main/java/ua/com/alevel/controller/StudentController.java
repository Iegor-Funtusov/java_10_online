package ua.com.alevel.controller;

import ua.com.alevel.entity.Student;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class StudentController {

    private final StudentService studentService = new StudentService();

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
            System.out.println("e = " + e.getMessage());
        }
    }

    void menu() {
        System.out.println();
        System.out.println("If you want create student please enter 1");
        System.out.println("If you want show all students please enter 2");
        System.out.println("If you want show student by id please enter 3");
        System.out.println("If you want update student please enter 4");
        System.out.println("If you want delete student please enter 5");
        System.out.println("If you want exit please enter exit");
    }

    void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> update(reader);
            case "5" -> delete(reader);
            case "exit" -> System.exit(0);
        }
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("StudentController.create");
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

    void readAll() {
        System.out.println("StudentController.readAll");
        List<Student> students = studentService.findAll();
        students.forEach(System.out::println);
    }

    void readById(BufferedReader reader) throws IOException {
        System.out.println("StudentController.readById");
        System.out.println("Please enter id");
        String id = reader.readLine();
        Student student = studentService.findById(id);
        System.out.println(
                "First name: " + student.getFirstName() +
                        ", Last name: " + student.getLastName() +
                        ", Age: " + student.getAge());
    }

    void update(BufferedReader reader) throws IOException {
//        System.out.println("StudentController.update");
//        System.out.println("Please enter id");
//        String id = reader.readLine();
//        Student student = studentService.findById(id);
//        if (student != null) {
//            System.out.println("Please enter first name");
//            String firstName = reader.readLine();
//            System.out.println("Please enter last name");
//            String lastName = reader.readLine();
//            System.out.println("Please enter age");
//            String ageString = reader.readLine();
//            int age = Integer.parseInt(ageString);
//            student = new Student();
//            student.setFirstName(firstName);
//            student.setLastName(lastName);
//            student.setAge(age);
//            student.setId(id);
//            studentService.update(student);
//        } else {
//            System.out.println("student not found");
//        }
    }

    void delete(BufferedReader reader) throws IOException {
        System.out.println("StudentController.delete");
        System.out.println("Please enter id");
        String id = reader.readLine();
        studentService.delete(id);
        Student student = studentService.findById(id);
        if (student == null) {
            System.out.println("Student was deleted");
        }
    }
}
