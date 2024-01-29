package ua.com.alevel.map;

import ua.com.alevel.Student;

import java.util.*;

public class MapTest {

    Map<Student, String> hashMap = new HashMap<>();
    Map<Student, String> linkedHashMap = new LinkedHashMap<>();
    Map<Student, String> treeMap = new TreeMap<>();

    public void test() {
        String id = UUID.randomUUID().toString();
        Student s1 = new Student();
        Student s2 = new Student();
        Student s3 = new Student();
        Student s4 = new Student();

        s1.setId(id);
        s1.setName("S1");
        s1.setAge(22);

        s4.setId(id);
        s4.setName("S4");
        s4.setAge(22);

        s2.setId(id);
        s2.setName("S2@");
        s2.setAge(21);

        s3.setId(id);
        s3.setName("S3");
        s3.setAge(23);

        Map<Student, String> treeMapNew = new TreeMap<>(new StudentComparator());

        treeMapNew.put(s4, "s1111111111111");
        treeMapNew.put(s1, "s1111111111111");
        treeMapNew.put(s2, "s1111111111111");
        treeMapNew.put(s3, "s1111111111111");
        treeMapNew.put(s1, "s1111111111112");

        treeMapNew.forEach((k, v) -> {
            System.out.println("k = " + k);
            System.out.println("v = " + v);
        });

//        hashMap.put(s1, "s1111111111111");
//        hashMap.put(s2, "s2");
//        hashMap.put(s3, "s3");
//
//        linkedHashMap.put(s1, "s1");
//        linkedHashMap.put(s2, "s2");
//        linkedHashMap.put(s3, "s3");
//
//        System.out.println("hashMap");
//        hashMap.forEach((k, v) -> {
//            System.out.println("k = " + k);
//            System.out.println("v = " + v);
//        });
//        System.out.println("value by S1 " + hashMap.get(s1));
//
//        System.out.println("linkedHashMap");
//        linkedHashMap.forEach((k, v) -> {
//            System.out.println("k = " + k);
//            System.out.println("v = " + v);
//        });





//        System.out.println(s1.equals(s2));
//        System.out.println("s1 address: " + s1);
//        System.out.println("s1 hashCode: " + s1.hashCode());
//        System.out.println("s2 hashCode: " + s2.hashCode());
//        System.out.println(s1.hashCode() == s2.hashCode());
//        s1 = s2;
//        System.out.println(s1.equals(s2));
    }
}
