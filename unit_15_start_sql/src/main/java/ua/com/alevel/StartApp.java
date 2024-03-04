package ua.com.alevel;

import ua.com.alevel.controller.StudentController;
import ua.com.alevel.exception.ExceptionHandler;

public class StartApp implements Runnable {

    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        StudentController studentController = new StudentController();
        studentController.start();
    }
}
