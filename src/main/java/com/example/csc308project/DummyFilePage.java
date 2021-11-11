package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DummyFilePage {
    public VBox DummyFileLayout() {
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text dummyText = new Text("dummy file");

        mainVBox.getChildren().addAll(dummyText);
        return mainVBox;
    }
}
