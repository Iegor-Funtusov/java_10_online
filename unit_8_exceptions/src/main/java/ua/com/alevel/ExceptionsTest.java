package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExceptionsTest {

    public void test() throws IOException {
//        int i = 10 / 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        observeResult(bufferedReader.readLine());
    }

    private void observeResult(String line) {
        try {
            int a = 10 / getInteger(line);
            System.out.println("a = " + a);
        } catch (ArithmeticException e) {
            System.out.println("incorrect number: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("can not convert string to number: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("unknown error: " + e.getMessage());
        }

        throw new RuntimeException("this is the end");
    }

    private Integer getInteger(String line) throws NumberFormatException {
        return Integer.parseInt(line);
    }
}
