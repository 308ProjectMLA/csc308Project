package com.example.csc308project;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class FileSelectPage {
    Button createButton;
    Button deleteButton;
    Button requestButton;

    String fileInQuestion;

    boolean somethingClicked = false;

    static final int GRID_SIZE = 5;
    static final int ITEM_SIZE = 80;

    public static final String PAGE_NAME = "viewFiles";

    private static final Logger LOGGER = Logger.getLogger( FileSelectPage.class.getName());

    public VBox fileSelectLayout(Stage stage) {
        Main.updateTitle(stage, "File Selection");

        VBox mainVBox = new VBox(10);

        mainVBox.setAlignment(Pos.TOP_CENTER);
        mainVBox.setPadding(new Insets(5 ,5, 150, 5));
        Label testText = new Label("File Selection");
        testText.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        testText.setPadding(new Insets(40, 0 , 150, 0));

        HBox otherStuff = new HBox(10);
        otherStuff.setAlignment(Pos.CENTER);
        createButton = new Button("Create File");
        deleteButton = new Button("Delete File");
        requestButton = new Button("Request Access");

        createButton.setId(Main.BUTTON_ID);
        createButton.getStylesheets().add(Main.BUTTON_STYLE);
        deleteButton.setId(Main.BUTTON_ID);
        deleteButton.getStylesheets().add(Main.BUTTON_STYLE);
        requestButton.setId(Main.BUTTON_ID);
        requestButton.getStylesheets().add(Main.BUTTON_STYLE);


        otherStuff.getChildren().addAll(requestButton, createButton,deleteButton);

        // Grid for files
        GridPane fileBox = new GridPane();
        fileBox.setMinWidth(Main.PAGE_WIDTH - 100);
        fileBox.setAlignment(Pos.TOP_LEFT);

        List<File> files = FileSelectController.getFiles();

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

            temp.setId("reg-yellow");
            temp.getStylesheets().add(Main.BUTTON_STYLE);

            temp.setPrefSize(ITEM_SIZE, ITEM_SIZE);
            temp.setGraphic(folderView);


            temp.setOnAction(actionEvent -> selectFile(stage, f));

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
        int i = 0;
        int j = 0;
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
        createButton.setOnAction(actionEvent ->
            Main.updatePage(stage, cfp.createFileLayout(stage), PAGE_NAME));

        deleteButton.setOnAction(actionEvent -> {
            try {
                FileSelectController.deleteFile(fileInQuestion);
                Main.updatePage(stage, this.fileSelectLayout(stage), PAGE_NAME);
            } catch (Exception ignored) {
                LOGGER.log(Level.WARNING, "Exception thrown");
            }
        });
        RequestAccessPage rap = new RequestAccessPage();
        requestButton.setOnAction(actionEvent -> {
            //sends request
            if(somethingClicked) {
                String bruh = "";
                for (int k = 0; k < fileInQuestion.length() - 4; k++) {
                    bruh = bruh + fileInQuestion.charAt(k);
                }
                Main.updatePage(stage, rap.requestAccessLayout(stage, bruh), PAGE_NAME);
            }
        });

            Main.updatePage(stage, rap.requestAccessLayout(stage, fileInQuestion), PAGE_NAME);

        mainVBox.getChildren().addAll(testText, sp, otherStuff);
        mainVBox.setStyle("-fx-background-color: #9da5b0;");

        return mainVBox;
    }

    private void selectFile(Stage stage, File f){
        somethingClicked = true;
        if (f.getName().equals(fileInQuestion)){
            //second click actually opens the file
            if (FileSelectController.allowView(f.getName())) {
                ViewFilePage vfp = new ViewFilePage();
                Main.updatePage(stage, vfp.viewFilePageLayout(stage, f.getName()), PAGE_NAME);
            } else {
                showDialog(f.getName());
            }
        } else {
            //first click updates fileInQuestion
            fileInQuestion = f.getName();
        }
    }

    private void showDialog(String filename) {
        Dialog<String> dialog = new Dialog<>();

        dialog.setTitle("Access Denied");
        dialog.setContentText("You do not have permission to view " + filename +
                "\nUse the 'Request Access' button to request access from a supervisor");

        ButtonType bt = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(bt);
        dialog.showAndWait();
    }
    
}