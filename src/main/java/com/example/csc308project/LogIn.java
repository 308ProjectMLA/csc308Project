package com.example.csc308project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogIn extends Application {

    Label label1;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("Multi-Level Authorization Manager - Login");
        label1 = new Label("Multi-Level Authorization Manager");
        //label1.setMinSize(50,50);
        label1.setMinHeight(100);
        label1.setMaxHeight(100);
        VBox root = new VBox();
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        root.getChildren().addAll(label1);
        stage.show();
    }
}
