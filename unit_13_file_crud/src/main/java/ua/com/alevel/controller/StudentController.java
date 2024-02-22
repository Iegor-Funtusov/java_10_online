package ua.com.alevel.controller;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class StudentController {

    private final StudentDao studentDao = new StudentDaoImpl();

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
        System.out.println("If you want exit please enter 5");
    }

    private void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> readById(reader);
            case "4" -> delete(reader);
            case "5" -> System.exit(0);
        }
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
        studentDao.create(student);
    }

    private void readById(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        Optional<Student> studentOptional = studentDao.findById(reader.readLine());
        studentOptional.ifPresent(student -> System.out.println("student = " + student));
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Please enter id");
        studentDao.delete(reader.readLine());
    }

    private void readAll() {
        studentDao.findAll().forEach(System.out::println);
    }
}
