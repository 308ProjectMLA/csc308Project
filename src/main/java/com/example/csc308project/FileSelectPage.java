package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

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
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        Text testText = new Text("file selection");
        selectButton = new Button("Select");

        HBox otherStuff = new HBox();
        otherStuff.setAlignment(Pos.CENTER);
        createButton = new Button("Create File");
        deleteButton = new Button("Delete File");
        otherStuff.getChildren().addAll(createButton,deleteButton);

        Image file = new Image("file.png");
        ImageView fileView1 = new ImageView(file);
        ImageView fileView2= new ImageView(file);
        ImageView fileView3 = new ImageView(file);
        ImageView fileView4 = new ImageView(file);
        //view.setFitHeight(80);
        //view.setPreserveRatio(true);

        HBox filesBox = new HBox();
        filesBox.setAlignment(Pos.CENTER);
        file1 = new Button("file 1");
        file1.setGraphic(fileView1);
        file1.setContentDisplay(ContentDisplay.TOP);

        file2 = new Button("file 2");
        file2.setGraphic(fileView2);
        file2.setContentDisplay(ContentDisplay.TOP);

        file3 = new Button("file 3");
        file3.setGraphic(fileView3);
        file3.setContentDisplay(ContentDisplay.TOP);

        file4 = new Button("file 4");
        file4.setGraphic(fileView4);
        file4.setContentDisplay(ContentDisplay.TOP);

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
            }
        });
        CreateFilePage cfp = new CreateFilePage();
        createButton.setOnAction(actionEvent -> {
            Main.updatePage(cfp.CreateFileLayout());
        });
        DeleteFilePage delfp = new DeleteFilePage();
        deleteButton.setOnAction(actionEvent -> {
            Main.updatePage(delfp.DeleteFileLayout());
        });
        mainVBox.getChildren().addAll(testText, filesBox, selectButton, otherStuff);

        return mainVBox;
    }
}
