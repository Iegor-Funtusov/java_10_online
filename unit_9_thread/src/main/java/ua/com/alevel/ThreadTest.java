package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ThreadTest {

    public void test() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Please, select your options:");
            String select;
            menu();
            while ((select = bufferedReader.readLine()) != null) {
                threadTest(select);
                menu();
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create thread, please enter 1");
        System.out.println("If you want create and incorrect stop thread, please enter 2");
        System.out.println("If you want create and correct stop thread, please enter 3");
    }

    private void threadTest(String select) {
        switch (select) {
            case "1" -> createThread();
            case "2" -> incorrectStopThread();
            case "3" -> correctStopThread();
        }
    }

    private void createThread() {
        List<CustomThread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new CustomThread("Thread_" + i));
        }
        for (CustomThread thread : threads) {
            thread.start();
        }

//        for (int i = 0; i < 10; i++) {
//            Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
//            Thread thread = new Thread(runnable);
//            thread.start();
//        }
    }

    private void incorrectStopThread() {
        Thread thread = new CustomThread("incorrectStopThread");
        thread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.stop();
    }

    private void correctStopThread() {
        Thread thread = new CustomThread("correctStopThread");
        thread.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread.interrupt();
    }
}
