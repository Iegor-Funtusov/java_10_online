package ua.com.alevel;

public class ThreadCrudMain {
    public static void main(String[] args) {
        System.out.println("Start");
        StartApp startApp = new StartApp();
        startApp.run();
        System.out.println("Finish");
    }
}