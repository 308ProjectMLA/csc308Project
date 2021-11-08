package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class fileSelectPage {
    public VBox fileSelectLayout(Stage primaryStage) {
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text testText = new Text("file selection bb");

        mainVBox.getChildren().addAll(testText);

        return mainVBox;
    }
}