package ua.com.alevel;

import java.util.Objects;

//public class Student implements Comparable<Student> {
public class Student {

    private String id;
    private String name;
    private int age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;

        if (age != student.age) return false;
        if (!Objects.equals(id, student.id)) return false;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

//    @Override
//    public int compareTo(Student o) {
//        int compareByAge = 0;
//        if (this.age > o.age) {
//            compareByAge = 1;
//        } else if (this.age == o.age) {
//            compareByAge = 0;
//        } else {
//            compareByAge = -1;
//        }
//
//        if (compareByAge == 0) {
//            return this.name.compareTo(o.name);
//        }
//
//        return compareByAge;
//    }
}
