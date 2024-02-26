package ua.com.alevel;

public sealed interface StudentService permits StudentServiceImpl1 {

    void create(Student student);

    default void blaBla() {
        System.out.println("StudentService.blaBla");
    }
}
