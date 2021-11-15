package com.example.csc308project;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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

    static final int GRID_SIZE = 6;

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


        GridPane fileBox = new GridPane();
        fileBox.setMinWidth(Main.PAGE_WIDTH - NavBar.BAR_WIDTH);
        fileBox.setVgap(10);
        fileBox.setHgap(10);
        fileBox.setAlignment(Pos.TOP_LEFT);

        // Configure the columns
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100.00 / GRID_SIZE);
        cc.setHalignment(HPos.CENTER);
        fileBox.getColumnConstraints().add(cc);

        // Loop over the files and add them to the list
        File dir = new File("data/");
        ArrayList<Button> buttons = new ArrayList<>();
        for (File f : dir.listFiles()) {
            if (f.getName().contains(".txt")) {
                Button temp = new Button(f.getName());
                // Set button action
                temp.setOnAction(actionEvent -> {
                    System.out.println("Filename: " + f.getName());
                });
                buttons.add(temp);
            }
        }

        // Add the buttons to the pane
        int i = 0, j = 0;
        for (Button b : buttons) {
            if (i >= GRID_SIZE) {
                i = 0;
                j++;
            }
            fileBox.add(b, i, j);
            i++;
        }

        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setContent(fileBox);
        sp.setFitToWidth(true);

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
        mainVBox.getChildren().addAll(testText, sp, selectButton, otherStuff);

        return mainVBox;
    }
}
