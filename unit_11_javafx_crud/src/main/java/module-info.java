module ua.com.alevel {
    requires javafx.controls;
    requires javafx.fxml;


    opens ua.com.alevel to javafx.fxml;
    exports ua.com.alevel;
    exports ua.com.alevel.controller;
    exports ua.com.alevel.entity;
    exports ua.com.alevel.service;
    exports ua.com.alevel.db;

    opens ua.com.alevel.controller to javafx.fxml;
}
