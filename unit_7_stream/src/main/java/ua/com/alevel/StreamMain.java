package ua.com.alevel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamMain {

    public static void main(String[] args) {
        // 1 sort
        // 2 unique
        // 3 *2

//        Stream<Integer>;


        // Convert operator -> stream -> stream
        // Terminal operator -> stream -> object

//        List<Integer> integers = Arrays.asList(1,3,2,5,9,3,6,8,3);
//
//        List<Integer> integerList = integers
//                .stream()
//                .sorted()
//                .distinct()
//                .map(i -> i * 2)
//                .toList();
//
//        System.out.println("integerList = " + integerList);
//        System.out.println("integers = " + integers);


//        Student student = new Student(1, "Petro");
//        ImmutableStudent immutableStudent = new ImmutableStudent(1, "Petro");
//        System.out.println("student = " + student);
//        System.out.println("immutableStudent = " + immutableStudent);
//
//        Class<?> studentClass = immutableStudent.getClass();
//        Field[] fields = studentClass.getDeclaredFields();
//        for (Field field : fields) {
//            try {
//                field.setAccessible(true);
//                if (field.getType().isAssignableFrom(Integer.class)) {
//                    field.set(immutableStudent, 2);
//                }
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        System.out.println("student = " + student);

        new StreamTest().test();
    }
}
