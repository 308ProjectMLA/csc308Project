package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class DummyFilePage {
    Button backButton;
    public VBox DummyFileLayout() {
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text dummyText = new Text("dummy file");
        backButton = new Button("back");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout());
        });

        mainVBox.getChildren().addAll(dummyText,backButton);
        return mainVBox;
    }
}
