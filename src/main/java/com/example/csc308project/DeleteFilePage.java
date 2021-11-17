package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class DeleteFilePage {
    Button backButton;
    Button deleteButton;
    private TextField fileName;
    boolean result;
    Label suc;
    boolean fileDeletionAttempted;

    public VBox DeleteFileLayout() {
        Main.updateTitle("Delete a File");
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);

        suc = new Label("");
        fileDeletionAttempted = false;
        backButton = new Button("back");

        //get name of file to be deleted
        fileName  = new TextField();
        fileName.setPromptText("Enter new file name");
        fileName.setMaxWidth(200);

        deleteButton = new Button("delete");
        deleteButton.setOnAction(actionEvent -> {
            //checks to see if the filename is actually an existing file name
            //deletes the file
            //prints success message or fail message
        });

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout());
        });

        HBox buttBox = new HBox();
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(deleteButton, backButton);

        mainVBox.getChildren().addAll(fileName, buttBox, suc);
        return mainVBox;
    }
}
