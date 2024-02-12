package ua.com.alevel;

public class CustomThread extends Thread {

    public CustomThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("thread main: " + name);
        while (true) {
            System.out.println("running ...");
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
        }
        System.out.println(name + " is stopped");
    }
}
