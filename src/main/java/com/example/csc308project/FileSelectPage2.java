package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FileSelectPage2 {
    Label label1;
    Button backButton;
    Button selectButton;

    public VBox fileSelectLayout() {
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text testText = new Text("file selection");
        backButton = new Button("Back");
        selectButton = new Button("Select");

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //go back to prev screen on stack
            }
        });
        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //attempt to access file
            }
        });

        mainVBox.getChildren().addAll(testText, selectButton, backButton);

        return mainVBox;
    }
}