package ua.com.alevel;

import java.io.*;

public class RWTest {

//    Reader reader;
//    Writer writer;

//    InputStream inputStream;
//    OutputStream outputStream;

    private final File file = new File("test1.txt");

    public void test() {
//        symbolWrite();
//        byteWrite();
//        byteRead();
        symbolRead();
    }

    private void symbolWrite() {
        try(FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write("\n");
            fileWriter.write("Hello1");
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void byteWrite() {
        byte[] bytes = new byte[]{72,101,108};
        try(OutputStream outputStream = new FileOutputStream(file, true)) {
            outputStream.write(10);
            outputStream.write(bytes);
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void byteRead() {
        try(InputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = inputStream.readAllBytes();
            for (byte aByte : bytes) {
                System.out.println("aByte = " + (char)aByte);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private void symbolRead() {
        try(
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                ) {
            String text = "";
            while (bufferedReader.ready()) {
                text = bufferedReader.readLine();
                System.out.println("text = " + text);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
