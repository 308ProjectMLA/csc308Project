package com.example.csc308project;

import javafx.geometry.Pos;
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
            Main.updatePage(primaryStage, viewRequests.pageLayout(primaryStage));
        });

        ModifyPermPage modifyFilePerm = new ModifyPermPage();
        Button modifyFilePermButt = new Button("Modify File Permissions");
        modifyFilePermButt.setOnAction(actionEvent -> {
            Main.updatePage(primaryStage, modifyFilePerm.pageLayout(primaryStage));
        });


        pageVBox.getChildren().addAll(pageTitle, viewRequestsButton, modifyFilePermButt);
        pageVBox.setAlignment(Pos.TOP_CENTER);

        return pageVBox;
    }
}
