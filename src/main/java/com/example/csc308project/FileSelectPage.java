package com.example.csc308project;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.Comparator;

class FileSelectPage {
    Button selectButton;
    Button createButton;
    Button deleteButton;

    static final int GRID_SIZE = 5;
    static final int ITEM_SIZE = 80;

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

        // Grid for files
        GridPane fileBox = new GridPane();
        fileBox.setMinWidth(Main.PAGE_WIDTH - 100);
        fileBox.setAlignment(Pos.TOP_LEFT);

        // Loop over the files and add them to the list
        // TODO Should we move this to the controller?
        File dir = new File("data/");
        ArrayList<VBox> buttonBox = new ArrayList<>();
        for (File f : dir.listFiles()) {
            if (!f.getName().contains(".txt")) {
                continue;
            }

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
            temp.setOnAction(actionEvent -> {
                System.out.println("Filename: " + f.getName());
            });

            // Label below the button
            Label name = new Label(f.getName());
            name.setWrapText(true);
            name.setMaxWidth(ITEM_SIZE);

            vb.getChildren().addAll(temp, name);
            buttonBox.add(vb);
        }

        // Sort the files
        // TODO Move to the controller
        buttonBox.sort(new Comparator<VBox>() {
            public int compare(VBox vb1, VBox vb2) {
                Label l1 = (Label) vb1.getChildren().get(1);
                Label l2 = (Label) vb2.getChildren().get(1);
                System.out.println(l1.getText());
                System.out.println(l2.getText());
                System.out.println(l1.getText().compareTo(l2.getText()) + "\n");
                return l1.getText().compareTo(l2.getText());
            }
        });

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
