package ua.com.alevel;

import java.io.File;

public class SummaryMain {
    public static void main(String[] args) {
        System.out.println("Hello SummaryMain!");

        String osName = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        System.out.println("osName = " + osName);
        System.out.println("userName = " + userName);

        File root = null;
        if (osName.equals("Mac OS X")) {
            root = new File("/Users/" + userName + "/");
            System.out.println("root = " + root.getAbsolutePath());
            for (File file : root.listFiles()) {
                System.out.println("file = " + file.getAbsolutePath());
            }
        }

//        BaseEntity.InnerBaseEntity innerBaseEntity = new BaseEntity.InnerBaseEntity();
    }
}
