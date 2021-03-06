package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateFilePage {
    Button backButton;
    Button createButton;
    Label suc;
    Label pageTitle;
    Label prompt;
    boolean fileCreationAttempted;

    private static final Logger LOGGER = Logger.getLogger( CreateFilePage.class.getName());

    public VBox createFileLayout(Stage stage) {
        Main.updateTitle(stage,"Create New File");
        VBox mainVBox = new VBox(Main.TOP_PAD * 3);
        mainVBox.setAlignment(Pos.TOP_CENTER);

        pageTitle = new Label("Create a file");
        pageTitle.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pageTitle.setPadding(new Insets(40, 0 , 200, 0 ));
        prompt = new Label("Enter new file name:");

        suc = new Label("");
        fileCreationAttempted = false;

        //get name for new file
        TextField fileName = new TextField();
        fileName.setPromptText("Enter new file name");
        fileName.setMaxWidth(200);

        //create button
        createButton = new Button("Create");
        createButton.setDefaultButton(true);
        createButton.setOnAction(actionEvent -> {
            try {
                if (CreateFileController.createFile(fileName.getCharacters().toString())) {
                    ViewFilePage vfp = new ViewFilePage();
                    Main.updatePage(stage, vfp.viewFilePageLayout(stage,fileName.getCharacters().toString() + ".txt"), FileSelectPage.PAGE_NAME);
                }
                else {
                    suc.setText("File creation failed");
                }
            } catch (Exception ignored) {
                LOGGER.log(Level.WARNING, "Exception thrown");
            }
        });

        backButton = new Button("Back");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent ->
                Main.updatePage(stage, fsp.fileSelectLayout(stage), FileSelectPage.PAGE_NAME));

        createButton.setId(Main.BUTTON_ID);
        createButton.getStylesheets().add(Main.BUTTON_STYLE);
        backButton.setId(Main.BUTTON_ID);
        backButton.getStylesheets().add(Main.BUTTON_STYLE);

        HBox buttBox = new HBox(Main.SIDE_PAD * 2);
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(createButton, backButton);

        mainVBox.getChildren().addAll(pageTitle, prompt, fileName, buttBox, suc);
        mainVBox.setStyle("-fx-background-color: #9da5b0;");

        return mainVBox;
    }
}