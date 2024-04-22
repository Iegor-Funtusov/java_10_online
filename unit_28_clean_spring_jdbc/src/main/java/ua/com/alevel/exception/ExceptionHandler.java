package ua.com.alevel.exception;

import ua.com.alevel.StartApp;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(e.getMessage());
        new Thread(new StartApp()).start();
    }
}
