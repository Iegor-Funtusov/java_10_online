package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IfElse {

    public void test() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a value");
        String text = bufferedReader.readLine();

        int res = Integer.parseInt(text);
//        boolean b = res == 10;

        if (res == 10) {
            System.out.println("10");
        } else if (res == 20) {
            System.out.println("20");
//        } else if (res < 30) {
        } else if (res == 30) {
            System.out.println("res < 30");
        } else {
            System.out.println("Nothing");
        }

        switch (res) {
            case 10 -> System.out.println("10");
            case 20 -> System.out.println("20");
            case 30 -> System.out.println("30");
            default -> System.out.println("nothing");
        }

        int a = 10;

        boolean b;
        if (a == 10) {
            b = true;
        } else {
            b = false;
        }
        b = a == 10 ? true : false;


//        if (a == 10) some
//        some unless a == 10

    }
}
