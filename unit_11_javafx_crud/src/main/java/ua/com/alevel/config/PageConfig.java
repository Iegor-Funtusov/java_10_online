package ua.com.alevel.config;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ua.com.alevel.reactiv.NativePubSub;

import java.io.IOException;
import java.util.Map;

public class PageConfig {

    private final Stage primaryStage;
    private final Map<String, FXMLLoader> loaderPageMap;
    private BorderPane rootLayout;
    private AnchorPane studentsView;
    private AnchorPane groupsView;

    public PageConfig(Stage stage, Map<String, FXMLLoader> loaderPageMap) {
        this.primaryStage = stage;
        this.loaderPageMap = loaderPageMap;
        this.primaryStage.setTitle("Java FX CRUD");
        initRootLayout(loaderPageMap.get(LoaderPage.LAYOUT.getView()));

        NativePubSub.getInstance().subscribe(this::switchPage);
    }

    private void initRootLayout(FXMLLoader loader) {
        try {
            this.rootLayout = loader.load();
            Rectangle2D screen = Screen.getPrimary().getBounds();
            double maxX = screen.getMaxX() / 2;
            double maxY = screen.getMaxY() / 2;
            Scene scene = new Scene(rootLayout, maxX, maxY);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showStudents(FXMLLoader loader) {
        try {
            if (studentsView == null) {
                studentsView = loader.load();
            }
            rootLayout.setCenter(studentsView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showGroups(FXMLLoader loader) {
        try {
            if (groupsView == null) {
                groupsView = loader.load();
            }
            rootLayout.setCenter(groupsView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchPage(LoaderPage page) {
        switch (page) {
            case STUDENTS -> showStudents(this.loaderPageMap.get(LoaderPage.STUDENTS.getView()));
            case GROUPS -> showGroups(this.loaderPageMap.get(LoaderPage.GROUPS.getView()));
        }
    }
}
