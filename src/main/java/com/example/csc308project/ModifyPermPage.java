package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModifyPermPage {

    public VBox pageLayout(Stage primaryStage) {
        VBox pageVBox = new VBox(25);
        VBox buttonVBox = new VBox(10);
        HBox titleBox = new HBox(5);

        //create page title
        titleBox.setAlignment(Pos.CENTER);
        Text title = new Text("Modify Permissions\t\t");

        //back button
        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button backButton = new Button("Back to Permissions Page");
        backButton.setOnAction(actionEvent -> {
            HelloApplication.updatePage(primaryStage, managePermissionPage.pageLayout(primaryStage));
        });

        titleBox.getChildren().addAll(title, backButton);

        //create page content
        Button selectFile = new Button("Select File");
        selectFile.setOnAction(actionEvent -> {

        });

        Button modGroupButton = new Button("Add/Remove Group");
        modGroupButton.setOnAction(actionEvent -> {

        });
        Button modUserButton = new Button("Add/Remove User");
        modUserButton.setOnAction(actionEvent -> {

        });
        Button saveButton = new Button("Save");
        saveButton.setOnAction(actionEvent -> {

        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(actionEvent -> {

        });
        buttonVBox.getChildren().addAll(selectFile, modGroupButton, modUserButton, saveButton, cancelButton);
        buttonVBox.setAlignment(Pos.CENTER);

        //create page
        pageVBox.getChildren().addAll(titleBox, buttonVBox);

        return pageVBox;
    }

}
