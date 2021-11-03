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
            primaryStage.setScene(new Scene(viewRequests.pageLayout(primaryStage), 500, 300));
        });

        ModifyPermPage modifyFilePerm = new ModifyPermPage();
        Button modifyFilePermButt = new Button("Modify File Permissions");
        modifyFilePermButt.setOnAction(actionEvent -> {
            primaryStage.setScene(new Scene(modifyFilePerm.pageLayout(primaryStage), 500, 300));
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
