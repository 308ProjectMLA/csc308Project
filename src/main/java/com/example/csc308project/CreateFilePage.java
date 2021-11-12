package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class CreateFilePage {
    Button backButton;
    public VBox CreateFileLayout() {
        Main.updateTitle("Create New File");
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        backButton = new Button("back");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout());
        });

        mainVBox.getChildren().addAll(backButton);
        return mainVBox;
    }
}
