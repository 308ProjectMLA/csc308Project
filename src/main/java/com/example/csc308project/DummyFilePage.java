package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DummyFilePage {
    public VBox DummyFileLayout() {
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text dummyText = new Text("dummy file");

        mainVBox.getChildren().addAll(dummyText);
        return mainVBox;
    }
}
