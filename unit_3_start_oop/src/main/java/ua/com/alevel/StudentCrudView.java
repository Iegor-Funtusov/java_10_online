package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StudentCrudView {

    Student[] students = new Student[10];

    void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position = "";
        while ((position = reader.readLine()) != null) {
            crud(position, reader);
            menu();
        }
    }

    void menu() {
        System.out.println();
        System.out.println("If you want create student please enter 1");
        System.out.println("If you want show all students please enter 2");
        System.out.println("If you want exit please enter 3");
    }

    void crud(String position, BufferedReader reader) throws IOException {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> readAll();
            case "3" -> System.exit(0);
        }
    }

    void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter first name");
        String firstName = reader.readLine();
        System.out.println("Please enter last name");
        String lastName = reader.readLine();
        Student student = new Student();
        student.firstName = firstName;
        student.lastName = lastName;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = student;
                break;
            }
        }


//        Student[] students1 = new Student[20];
//        for (int i = 0; i < students1.length; i++) {
//            students1[i] = students[i];
//        }
//        for (int i = 0; i < students1.length; i++) {
//            if (students1[i] == null) {
//                students1[i] = student;
//                break;
//            }
//        }
    }

    void readAll() {
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                System.out.println("i: " + i + ", First name: "
                        + students[i].firstName + ", Last name: " + students[i].lastName);
            }
        }
    }
}
