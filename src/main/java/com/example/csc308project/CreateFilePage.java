package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.IOException;

public class CreateFilePage {
    Button backButton;
    Button createButton;
    Label suc;
    Label pageTitle;
    Label prompt;
    boolean fileCreationAttempted;

    public VBox createFileLayout() {
        Main.updateTitle("Create New File");
        VBox mainVBox = new VBox(Main.TOP_PAD * 3);
        mainVBox.setAlignment(Pos.TOP_CENTER);

        pageTitle = new Label("Create a file");
        pageTitle.setTextFill(Color.WHITE);
        pageTitle.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pageTitle.setPadding(new Insets(40, 0 , 200, 0 ));
        prompt = new Label("Enter new file name:");
        prompt.setTextFill(Color.WHITE);

        suc = new Label("");
        suc.setTextFill(Color.WHITE);
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
                    Main.updatePage(vfp.viewFilePageLayout(fileName.getCharacters().toString() + ".txt"), FileSelectPage.PAGE_NAME);
                }
                else {
                    suc.setText("File creation failed");
                }
            } catch (Exception ignored) {}
        });

        backButton = new Button("Back");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent ->
                Main.updatePage(fsp.fileSelectLayout(), FileSelectPage.PAGE_NAME));

        createButton.setId(Main.BUTTON_ID);
        createButton.getStylesheets().add(Main.BUTTON_STYLE);
        backButton.setId(Main.BUTTON_ID);
        backButton.getStylesheets().add(Main.BUTTON_STYLE);

        HBox buttBox = new HBox(Main.SIDE_PAD * 2);
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(createButton, backButton);

        mainVBox.getChildren().addAll(pageTitle, prompt, fileName, buttBox, suc);
        mainVBox.setStyle("-fx-background-image: url('file:img/network-background.png');");
        return mainVBox;
    }
}