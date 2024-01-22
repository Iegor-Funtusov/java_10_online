package ua.com.alevel;


import ua.com.alevel.entity.BaseEntity;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FinishCrudMain {

    public static void main(String[] args) throws IOException {

        Object[] some = new Object[10];
        some[0] = new Student();
        some[1] = new Group();
        some[2] = new GroupService();

        // дерево є дуб = false
        // дуб є дерево = true

        // BaseEntity is student = false
        // student is BaseEntity = true


        GroupService groupService = new GroupService();
        StudentService studentService = new StudentService();

        Student s1 = new Student();
        s1.setFirstName("q");
        s1.setLastName("q");
        s1.setAge(23);
        studentService.create(s1);

        Student s2 = new Student();
        s2.setFirstName("w");
        s2.setLastName("w");
        s2.setAge(23);
        studentService.create(s2);

        Group group = new Group();
        group.setName("JAVA");
        groupService.create(group);

        List<Student> students = studentService.allStudents();
        for (Student student : students) {
            System.out.println("student getId = " + student.getId());
            System.out.println("student getFirstName = " + student.getFirstName());
            System.out.println("student getLastName = " + student.getLastName());
            System.out.println("student getAge = " + student.getAge());
        }

        List<Group> groups = groupService.allGroups();
        for (Group group1 : groups) {
            System.out.println("group getId = " + group1.getId());
            System.out.println("group getName = " + group1.getName());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Please enter student id");
        String studentId = reader.readLine();

        System.out.println("Please enter group id");
        String groupId = reader.readLine();

        groupService.attachStudent(studentId, groupId);

        System.out.println("All students by group: " + groupId);
        List<Student> studentList = studentService.allStudentsByGroup(groupId);
        for (Student student : studentList) {
            System.out.println("student getId = " + student.getId());
            System.out.println("student getFirstName = " + student.getFirstName());
            System.out.println("student getLastName = " + student.getLastName());
            System.out.println("student getAge = " + student.getAge());
        }
    }
}
