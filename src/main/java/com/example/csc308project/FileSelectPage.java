package com.example.csc308project;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.ArrayList;

class FileSelectPage {
    Button createButton;
    Button deleteButton;
    Button requestButton;

    String fileInQuestion;
    int clicks = 0;

    static final int GRID_SIZE = 5;
    static final int ITEM_SIZE = 80;

    public VBox fileSelectLayout() {
        Main.updateTitle("File Selection");

        VBox mainVBox = new VBox(10);

        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(5 ,5, 5, 5));
        Text testText = new Text("File Selection");

        HBox otherStuff = new HBox(10);
        otherStuff.setAlignment(Pos.CENTER);
        createButton = new Button("Create File");
        deleteButton = new Button("Delete File");
        requestButton = new Button("Request Access");
        otherStuff.getChildren().addAll(requestButton, createButton,deleteButton);

        // Grid for files
        GridPane fileBox = new GridPane();
        fileBox.setMinWidth(Main.PAGE_WIDTH - 100);
        fileBox.setAlignment(Pos.TOP_LEFT);

        ArrayList<File> files = FileSelectController.getFiles();
        ArrayList<VBox> buttonBox = new ArrayList<>(files.size());
        // Loop over the files and add them to the list
        for (File f : files) {
            // Create image for the file
            Image folder = new Image("file:img/file-icon.png");
            ImageView folderView = new ImageView(folder);
            folderView.setFitHeight(ITEM_SIZE);
            folderView.setFitWidth(ITEM_SIZE);
            folderView.setPreserveRatio(true);

            // VBox to put button and text in
            VBox vb = new VBox(2);
            vb.setAlignment(Pos.TOP_CENTER);

            Button temp = new Button();
            temp.setPrefSize(ITEM_SIZE, ITEM_SIZE);
            temp.setGraphic(folderView);
            // Set button action
            // to file view page
            temp.setOnAction(actionEvent -> {
                if (f.getName().equals(fileInQuestion)){
                    //second click actually opens the file
                    ViewFilePage vfp = new ViewFilePage();
                    Main.updatePage(vfp.viewFilePageLayout(f.getName()));
                }else{
                    //first click updates fileInQuestion
                    fileInQuestion = f.getName();
                }
            });

            // Label below the button
            Label name = new Label(f.getName());
            name.setWrapText(true);
            name.setMaxWidth(ITEM_SIZE);

            vb.getChildren().addAll(temp, name);
            buttonBox.add(vb);
        }

        // Sort the files (comparator lambda)
        FileSelectController.sortButtons(buttonBox);

        // Configure the columns
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100.00 / GRID_SIZE);
        cc.setHalignment(HPos.CENTER);

        // Add the buttons to the pane
        int i = 0, j = 0;
        for (VBox v : buttonBox) {
            if (i >= GRID_SIZE) {
                i = 0;
                j++;
            }
            fileBox.add(v, i, j);
            i++;

            // Add column constraints to every column
            if (j == 0) {
                fileBox.getColumnConstraints().add(cc);
            }
        }

        // Allow it to scroll
        ScrollPane sp = new ScrollPane();
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setContent(fileBox);
        sp.setFitToWidth(true);

        CreateFilePage cfp = new CreateFilePage();
        createButton.setOnAction(actionEvent -> {
            Main.updatePage(cfp.createFileLayout());
        });

        deleteButton.setOnAction(actionEvent -> {
            
            try {
                File fileToDelete = new File("data/" + fileInQuestion);
                if (fileToDelete.delete()) {
                    //success
                    System.out.println("file deletion successful");
                    Main.updatePage(this.fileSelectLayout());
                } else {
                    //failed
                    System.out.println("file creation failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        requestButton.setOnAction(actionEvent -> {
            //sends request
        });
        mainVBox.getChildren().addAll(testText, sp, otherStuff);
        mainVBox.setStyle("-fx-background-color: #9da5b0;");

        return mainVBox;
    }
}