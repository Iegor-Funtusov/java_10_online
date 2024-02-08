package ua.com.alevel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExceptionsMain {

    public static void main(String[] args) throws IOException {
        System.out.println("ExceptionsMain");

        Throwable throwable;

        Error error;
        // ->
        OutOfMemoryError outOfMemoryError;
        StackOverflowError stackOverflowError;

        Exception exception;
        // ->
        IOException ioException;
        SQLException sqlException;
        RuntimeException runtimeException;

        new ExceptionsTest().test();


//        List<CustomThread> customThreads = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            customThreads.add(new CustomThread(i));
//        }
//
//        for (CustomThread customThread : customThreads) {
//            customThread.start();
//        }

        System.out.println("Finish");
    }
}
