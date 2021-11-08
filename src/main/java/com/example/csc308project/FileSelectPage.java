package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FileSelectPage {
    public VBox fileSelectLayout() {
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text testText = new Text("file selection");

        mainVBox.getChildren().addAll(testText);

        return mainVBox;
    }
}