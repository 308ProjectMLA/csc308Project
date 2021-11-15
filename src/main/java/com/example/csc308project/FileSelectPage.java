package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class FileSelectPage {
    Button selectButton;
    Button createButton;
    Button deleteButton;

    Button file1;
    Button file2;
    Button file3;
    Button file4;

    int fileNumber = 0;

    public VBox fileSelectLayout() {
        Main.updateTitle("File Selection");
<<<<<<< HEAD

=======
>>>>>>> main
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text testText = new Text("file selection");
        selectButton = new Button("Select");

        HBox otherStuff = new HBox();
        otherStuff.setAlignment(Pos.CENTER);
        createButton = new Button("Create File");
        deleteButton = new Button("Delete File");
        otherStuff.getChildren().addAll(createButton,deleteButton);

        HBox filesBox = new HBox();
        filesBox.setAlignment(Pos.CENTER);
        file1 = new Button("file 1");
        file2 = new Button("file 2");
        file3 = new Button("file 3");
        file4 = new Button("file 4");
        filesBox.getChildren().addAll(file1,file2,file3,file4);

        file1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileNumber = 1;
            }
        });
        file2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileNumber = 2;
            }
        });
        file3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileNumber = 3;
            }
        });
        file4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fileNumber = 4;
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //go to file creation screen
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //go to file deletion screen
            }
        });

        DummyFilePage dfp = new DummyFilePage();
        selectButton.setOnAction(actionEvent -> {
            if(fileNumber!=0) {
                Main.updatePage(dfp.DummyFileLayout());
                Main.updateTitle("File Name Here");
            }
        });
        CreateFilePage cfp = new CreateFilePage();
        createButton.setOnAction(actionEvent -> {
<<<<<<< HEAD
                Main.updatePage(cfp.CreateFileLayout());
        });
        DeleteFilePage delfp = new DeleteFilePage();
        deleteButton.setOnAction(actionEvent -> {
                Main.updatePage(delfp.DeleteFileLayout());
=======
            Main.updatePage(cfp.CreateFileLayout());
        });
        DeleteFilePage delfp = new DeleteFilePage();
        deleteButton.setOnAction(actionEvent -> {
            Main.updatePage(delfp.DeleteFileLayout());
>>>>>>> main
        });
        mainVBox.getChildren().addAll(testText, filesBox, selectButton, otherStuff);

        return mainVBox;
    }
}
