package ua.com.alevel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StudentController {

    @FXML
    private Label welcomeText1;

    public StudentController() {
//        welcomeText1.setText("Welcome to Students");
    }

    @FXML
    public void onStudentButtonClick() {
        welcomeText1.setText("Welcome to Group");
    }
}
