package com.example.csc308project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("MLA");
        VBox app = new VBox();
        Button uselessButton = new Button("Useless button");

        TestPage tp = new TestPage();
        Button changeScreen = new Button("Other screen");
        changeScreen.setOnAction(actionEvent -> {
            stage.setScene(new Scene(tp.testLayout(stage), 500, 300));
        });

        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button managePermissionButton = new Button("Manage Permissions");
        managePermissionButton.setOnAction(actionEvent -> {
            stage.setScene(new Scene(managePermissionPage.pageLayout(stage), 500, 300));
        });

        app.getChildren().addAll(uselessButton, changeScreen, managePermissionButton);
        Scene scene = new Scene(app);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}