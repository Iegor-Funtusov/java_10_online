package ua.com.alevel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.com.alevel.entity.Group;
import ua.com.alevel.reactiv.NativePubSub;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.impl.GroupServiceImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupViewController implements Initializable {

    private final GroupService groupService = new GroupServiceImpl();

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

    private ObservableList<Group> groups = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groups.addAll(groupService.findAll());
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

        NativePubSub.getInstance().subscribeGroup(this::updateGroups);
    }

    public void create() {
        Group group = new Group();
        group.setName(groupNameText.getText());
        groupService.create(group);
        NativePubSub.getInstance().publishGroup(true);
    }

    public void update() {
        Group group = new Group();
        group.setId(groupIdText.getText());
        group.setName(groupNameText.getText());
        groupService.update(group);
        NativePubSub.getInstance().publishGroup(true);
    }

    public void delete() {
        groupService.delete(groupIdText.getText());
        NativePubSub.getInstance().publishGroup(true);
    }

    private void updateGroups(Boolean b) {
        if (b) {
            groups = FXCollections.observableArrayList();
            groups.addAll(groupService.findAll());
            groupTable.setItems(groups);
        }
    }
}
