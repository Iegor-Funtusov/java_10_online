package ua.com.alevel;

import java.io.*;
import java.util.UUID;

public class SerialTest {

    private final Student student = generateStudent();

    public void test() {
        serial();
        deserial();
    }

    private void serial() {
        try(ObjectOutputStream outputStream =
                    new ObjectOutputStream(new FileOutputStream(FileType.STUDENT_NAME.getPath()))) {
            outputStream.writeObject(student);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deserial() {
        try(ObjectInputStream inputStream =
                    new ObjectInputStream(new FileInputStream(FileType.STUDENT_NAME.getPath()))) {
            Object o = inputStream.readObject();
            Student s = (Student) o;
            System.out.println("s = " + s);
            System.out.println("fn = " + s.getFullName());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Student generateStudent() {
        Student student = new Student();
        student.setAge(28);
        student.setId(UUID.randomUUID().toString());
        student.setFirstName("Taras");
        student.setLastName("Tarasov");
        student.setFullName("Taras Tarasov");
        return student;
    }
}
