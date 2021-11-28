package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;

public class RequestAccessPage {
    Button backButton;
    Label suc;
    Label pageTitle;
    CheckBox rButton;
    CheckBox wButton;
    Button submitButton;
    //String fileName;
    boolean requestAttempted;

    public VBox requestAccessLayout(String fileName) {
        Main.updateTitle("Request Access");
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);

        pageTitle = new Label("Request Access for: " + fileName);
        pageTitle.setTextFill(Color.WHITE);

        suc = new Label("");
        suc.setTextFill(Color.WHITE);
        requestAttempted = false;

        //get name of file being requested
         //fileName = f;

        //buttons bb (r, w, submit)
        rButton = new CheckBox("read");
        rButton.setTextFill(Color.WHITE);
        rButton.setIndeterminate(false);

        wButton = new CheckBox("read + write");
        wButton.setTextFill(Color.WHITE);
        wButton.setIndeterminate(false);

        submitButton = new Button("submit");

        backButton = new Button("back");

        submitButton.setId("round-yellow");
        submitButton.getStylesheets().add("file:cssfiles/yellowbutton.css");
        backButton.setId("round-yellow");
        backButton.getStylesheets().add("file:cssfiles/yellowbutton.css");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout(), FileSelectPage.PAGE_NAME);
        });

        //this will have the buttons for read and write
        HBox buttBox = new HBox();
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(submitButton,backButton);

        mainVBox.getChildren().addAll(pageTitle, rButton, wButton, buttBox, suc);
        mainVBox.setStyle("-fx-background-image: url('file:img/network-background.png');");

        return mainVBox;
    }
}