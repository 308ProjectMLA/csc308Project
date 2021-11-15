package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;

class FileSelectPage {
    Button selectButton;
    Button createButton;
    Button deleteButton;

    int fileNumber = 0;

    public VBox fileSelectLayout() {
        Main.updateTitle("File Selection");
        VBox mainVBox = new VBox(10);
        mainVBox.setAlignment(Pos.CENTER);
        Text testText = new Text("file selection");
        selectButton = new Button("Select");

        HBox otherStuff = new HBox(10);
        otherStuff.setAlignment(Pos.CENTER);
        createButton = new Button("Create File");
        deleteButton = new Button("Delete File");
        otherStuff.getChildren().addAll(createButton,deleteButton);

        HBox filesBox = new HBox(10);
        filesBox.setAlignment(Pos.CENTER);

        File dir = new File("data/");
        ArrayList<Button> buttons = new ArrayList<>();
        for (File f : dir.listFiles()) {
            if (f.getName().contains(".txt")) {
                Button temp = new Button(f.getName());
                temp.setOnAction(actionEvent -> {
                    System.out.println("Filename: " + f.getName());
                });
                buttons.add(temp);
            }
        }

        for (Button b : buttons) {
            filesBox.getChildren().add(b);
        }

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
