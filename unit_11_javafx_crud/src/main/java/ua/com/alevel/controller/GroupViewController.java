package ua.com.alevel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.com.alevel.entity.Group;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class GroupViewController implements Initializable {

    @FXML
    private TextField groupIdText;

    @FXML
    private TextField groupNameText;

    @FXML
    private Button createGroup;

    @FXML
    private Button updateGroup;

    @FXML
    private Button deleteGroup;

    @FXML
    private TableView<Group> groupTable;

    @FXML
    private TableColumn<Group, String> idColumn;

    @FXML
    private TableColumn<Group, String> nameColumn;

    private final ObservableList<Group> groups = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groups.addAll(generate());
        idColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Group, String>("name"));
        groupTable.setItems(groups);

        groupTable.setRowFactory(tv -> {
            TableRow<Group> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                Group rowData = row.getItem();
                groupIdText.setText(rowData.getId());
                groupNameText.setText(rowData.getName());
            });
            return row;
        });
    }

    public void create() {
        System.out.println("GroupViewController.create");
        Group group = new Group();
        group.setId(UUID.randomUUID().toString());
        group.setName(groupNameText.getText());
        groups.add(group);
    }

    public void update() {
        System.out.println("GroupViewController.update");
        Group group = groups.stream().filter(g -> g.getId().equals(groupIdText.getText())).findAny().get();
        group.setName(groupNameText.getText());
    }

    public void delete() {
        System.out.println("GroupViewController.delete");
        groups.removeIf(g -> g.getId().equals(groupIdText.getText()));
    }

    private List<Group> generate() {
        List<Group> groupList = new ArrayList<>();
        Group java = new Group();
        java.setName("JAVA");
        java.setId(UUID.randomUUID().toString());
        groupList.add(java);

        Group js = new Group();
        js.setId(UUID.randomUUID().toString());
        js.setName("JS");
        groupList.add(js);
        return groupList;
    }
}
