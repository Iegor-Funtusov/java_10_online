package ua.com.alevel.factory;

import ua.com.alevel.db.DbStudent;
import ua.com.alevel.db.ListStudent;
import ua.com.alevel.db.StudentStorage;

public class StudentStorageFactory {

    private final static StudentStorage studentStorage = new ListStudent();

    public static StudentStorage getStudentStorage() {
        return studentStorage;
    }
}
