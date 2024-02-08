package ua.com.alevel;

import java.util.Random;

public class CustomThread extends Thread {

    public CustomThread(int i) {
        super("Thread: " + i);
    }

    @Override
    public void run() {
        System.out.println("currentThread: " + Thread.currentThread().getName());
        int i = new Random().nextInt(2);
        System.out.println("i = " + (10 / i));
    }
}
