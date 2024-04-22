package ua.com.alevel;

import ua.com.alevel.controller.MainController;
import ua.com.alevel.exception.ExceptionHandler;

public class StartApp implements Runnable {

    private MainController mainController;

    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        mainController.start();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
