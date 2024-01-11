package ua.com.alevel;

import ua.com.alevel.controller.StudentController;

import java.io.IOException;

public class NextOOPMain {

    public static void main(String[] args) throws IOException {
        // Student -> base_entity +++
        //base_entity -> student ---
        StudentController studentController = new StudentController();
        studentController.start();

//        Test test = new Test();
//        test.s = "gssgs";
//
//        Test.s1 = "gagasgsa";
    }
}
