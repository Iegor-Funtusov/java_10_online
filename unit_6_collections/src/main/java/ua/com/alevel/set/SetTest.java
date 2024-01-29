package ua.com.alevel.set;

import ua.com.alevel.Student;

import java.util.*;

public class SetTest {

    Set<Student> hashSet = new HashSet<>();
    Set<Student> linkedHashSet = new LinkedHashSet<>();
    Set<Student> treeSet = new TreeSet<>();

    Map<Student, String> hm = new HashMap<>();

    public void test() {
        hm.keySet();

        hashSet.add(new Student());
        // ->
        hm.put(new Student(), null);
    }
}
