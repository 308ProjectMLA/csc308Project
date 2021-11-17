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
        VBox pageVBox = new VBox(Main.TOP_PAD);
        VBox buttonVBox = new VBox(Main.TOP_PAD);
        HBox titleBox = new HBox(Main.SIDE_PAD);

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
        Text fileTitle = new Text("File to Manage");
        ComboBox<String> fileSelector = new ComboBox<>();
        fileSelector.setPromptText("Select file to manage");

        fileSelector.getItems().add("File A");
        fileSelector.getItems().add("File B");
        fileSelector.getItems().add("File C");

        Text addGroupTitle = new Text("Group to add");
        TextField groupAdd  = new TextField();
        groupAdd.setPromptText("Enter group to add");
        groupAdd.setMaxWidth(Main.FIELD_WIDTH);


        Text delGroupTitle = new Text("Group to remove");
        TextField groupDel  = new TextField();
        groupDel.setPromptText("Enter group to remove");
        groupDel.setMaxWidth(Main.FIELD_WIDTH);

        Text addUserTitle = new Text("User to add");
        TextField userAdd  = new TextField();
        userAdd.setPromptText("Enter user to add");
        userAdd.setMaxWidth(Main.FIELD_WIDTH);

        Text removeUserTitle = new Text("User to remove");
        TextField userDel  = new TextField();
        userDel.setPromptText("Enter user to remove");
        userDel.setMaxWidth(Main.FIELD_WIDTH);

        Button saveButton = new Button("Submit");
        saveButton.setOnAction(actionEvent -> {

        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(actionEvent -> {
            Main.updatePage(managePermissionPage.pageLayout());
        });

        HBox box1 = new HBox(Main.SIDE_PAD);
        box1.getChildren().addAll(saveButton, cancelButton);
        box1.setAlignment(Pos.CENTER);

        buttonVBox.getChildren().addAll(fileTitle, fileSelector, addGroupTitle, groupAdd,
                delGroupTitle, groupDel, addUserTitle, userAdd, removeUserTitle, userDel, box1);
        buttonVBox.setAlignment(Pos.CENTER);

        //create page
        pageVBox.getChildren().addAll(titleBox, buttonVBox);

        return pageVBox;
    }

}
