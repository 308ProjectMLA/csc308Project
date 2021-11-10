package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModifyPermPage {

    public VBox pageLayout() {
        VBox pageVBox = new VBox(25);
        VBox buttonVBox = new VBox(10);
        HBox titleBox = new HBox(5);

        //create page title
        titleBox.setAlignment(Pos.CENTER);
        Text title = new Text("Modify Permissions\t\t");

        //back button
        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button backButton = new Button("Back to Manage Permissions");
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout());
        });

        titleBox.getChildren().addAll(title, backButton);

        //create page content
        ComboBox<String> fileSelector = new ComboBox<String>();
        fileSelector.setPromptText("Select file to manage");

        fileSelector.getItems().add("File A");
        fileSelector.getItems().add("File B");
        fileSelector.getItems().add("File C");

        TextField groupAdd  = new TextField();
        groupAdd.setPromptText("Enter group to add");
        groupAdd.setMaxWidth(200);

        TextField groupDel  = new TextField();
        groupDel.setPromptText("Enter group to remove");
        groupDel.setMaxWidth(200);

        TextField userAdd  = new TextField();
        userAdd.setPromptText("Enter user to add");
        userAdd .setMaxWidth(200);

        TextField userDel  = new TextField();
        userDel.setPromptText("Enter user to remove");
        userDel.setMaxWidth(200);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(actionEvent -> {

        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout());
        });
        buttonVBox.getChildren().addAll(fileSelector, groupAdd, groupDel, userAdd, userDel, saveButton, cancelButton);
        buttonVBox.setAlignment(Pos.CENTER);

        //create page
        pageVBox.getChildren().addAll(titleBox, buttonVBox);

        return pageVBox;
    }

}
