package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Circle {

    public void test() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a value");
        String text = bufferedReader.readLine();
        char[] chars = text.toCharArray();

        int a = 0;
        a = a + 1;
        a += 1;
        a++;

        int i = 0;
        for (i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }

        System.out.println();

        i = 0;
        while (i < chars.length) {
            System.out.println(chars[i]);
            i++;
        }
    }
}
