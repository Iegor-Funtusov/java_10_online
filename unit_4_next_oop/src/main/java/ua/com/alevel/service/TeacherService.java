package ua.com.alevel.service;

import ua.com.alevel.db.StudentStorage;
import ua.com.alevel.factory.StudentStorageFactory;

// Service class
public class TeacherService {

    StudentStorage studentStorage = StudentStorageFactory.getStudentStorage();
}
