package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagePermissionPage {


    public VBox pageLayout(Stage primaryStage) {

        VBox pageVBox = new VBox(15);
        Text pageTitle = new Text("Manage Permissions");

        ViewAccessRequestPage viewRequests = new ViewAccessRequestPage();
        Button viewRequestsButton = new Button("View Access Requests");
        viewRequestsButton.setOnAction(actionEvent -> {
            HelloApplication.updatePage(primaryStage, viewRequests.pageLayout(primaryStage));
        });

        ModifyPermPage modifyFilePerm = new ModifyPermPage();
        Button modifyFilePermButt = new Button("Modify File Permissions");
        modifyFilePermButt.setOnAction(actionEvent -> {
            HelloApplication.updatePage(primaryStage, modifyFilePerm.pageLayout(primaryStage));
        });

        TestPage createDelFile = new TestPage();
        Button createDelFileButt = new Button("Create/Delete File");
        createDelFileButt.setOnAction(actionEvent -> {

        });

        pageVBox.getChildren().addAll(pageTitle, viewRequestsButton, modifyFilePermButt, createDelFileButt);
        pageVBox.setAlignment(Pos.TOP_CENTER);

        return pageVBox;
    }
}
