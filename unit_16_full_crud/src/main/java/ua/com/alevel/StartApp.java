package ua.com.alevel;

import ua.com.alevel.controller.MainController;
import ua.com.alevel.exception.ExceptionHandler;

public class StartApp implements Runnable {

    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        MainController mainController = new MainController();
        mainController.start();
    }
}
