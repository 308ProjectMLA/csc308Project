package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestPage {
    public VBox testLayout(Stage primaryStage) {
        VBox testVBox = new VBox();
        testVBox.setAlignment(Pos.CENTER);
        Text testText = new Text("I'm a new page");

        testVBox.getChildren().addAll(testText);

        return testVBox;
    }
}
