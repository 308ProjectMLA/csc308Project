package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;

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

        suc = new Label("");
        requestAttempted = false;

        //get name of file being requested
         //fileName = f;

        //buttons bb (r, w, submit)
        rButton = new CheckBox("read");
        rButton.setIndeterminate(false);

        wButton = new CheckBox("read + write");
        wButton.setIndeterminate(false);

        submitButton = new Button("submit");

        backButton = new Button("back");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout(), FileSelectPage.PAGE_NAME);
        });

        //this will have the buttons for read and write
        HBox buttBox = new HBox();
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(submitButton,backButton);

        mainVBox.getChildren().addAll(pageTitle, rButton, wButton, buttBox, suc);
        mainVBox.setStyle("-fx-background-color: #9da5b0;");
        return mainVBox;
    }
}