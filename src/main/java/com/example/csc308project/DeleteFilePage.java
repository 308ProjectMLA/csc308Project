package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;

public class DeleteFilePage {
    Button backButton;
    Button deleteButton;
    private TextField fileName;
    Label pageTitle;
    Label suc;
    Label prompt;
    boolean fileDeletionAttempted;

    public VBox DeleteFileLayout() {
        Main.updateTitle("Delete a File");
        VBox mainVBox = new VBox(Main.TOP_PAD);
        mainVBox.setAlignment(Pos.CENTER);

        pageTitle = new Label("Delete a file");
        prompt = new Label("Enter a file name:");

        suc = new Label("");
        fileDeletionAttempted = false;
        backButton = new Button("back");

        //get name of file to be deleted
        fileName  = new TextField();
        fileName.setPromptText("Enter a file name");
        fileName.setMaxWidth(Main.FIELD_WIDTH);

        deleteButton = new Button("delete");
        deleteButton.setOnAction(actionEvent -> {
            try {
                File fileToDelete = new File("data/" + fileName.getCharacters().toString() + ".txt");
                if (fileToDelete.delete()) {
                    //success
                    suc.setText("file deletion successful");
                } else {
                    //failed
                    suc.setText("file creation failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout());
        });

        HBox buttBox = new HBox();
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(deleteButton, backButton);

        mainVBox.getChildren().addAll(pageTitle, prompt, fileName, buttBox, suc);
        return mainVBox;
    }
}
