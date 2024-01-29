package ua.com.alevel.map;

import ua.com.alevel.Student;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student left, Student right) {
        int compareByAge = 0;
        if (left.getAge() > right.getAge()) {
            compareByAge = 1;
        } else if (left.getAge() == right.getAge()) {
            compareByAge = 0;
        } else {
            compareByAge = -1;
        }

        if (compareByAge == 0) {
            return left.getName().compareTo(right.getName());
        }

        return compareByAge;
    }
}
