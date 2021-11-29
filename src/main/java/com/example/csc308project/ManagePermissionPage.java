package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ManagePermissionPage {

    public VBox pageLayout(Stage stage) {

        Main.updateTitle(stage,"Manage Permissions");

        VBox overallPageVBox = new VBox(Main.TOP_PAD);
        overallPageVBox.setAlignment(Pos.TOP_CENTER);
        VBox buttonVBox = new VBox(40);

        //creating header
        HBox header = new HBox();
        Text pageTitle = new Text("Manage Permissions");
        pageTitle.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        header.getChildren().addAll(pageTitle);
        header.setPadding(new Insets(40, 0 , 230, 0 ));
        header.setAlignment(Pos.TOP_CENTER);

        ViewAccessRequestPage viewRequests = new ViewAccessRequestPage();
        Button viewRequestsButton = new Button("View Access Requests");
        viewRequestsButton.setOnAction(actionEvent ->
            Main.updatePage(stage, viewRequests.pageLayout(stage),"managePermissions"));

        ModifyPermPage modifyFilePerm = new ModifyPermPage();
        Button modifyFilePermButt = new Button("Modify File Permissions");
        modifyFilePermButt.setOnAction(actionEvent ->
            Main.updatePage(stage, modifyFilePerm.pageLayout(stage), "managePermissions"));

        viewRequestsButton.setId(Main.BUTTON_ID);
        viewRequestsButton.getStylesheets().add(Main.BUTTON_STYLE);
        modifyFilePermButt.setId(Main.BUTTON_ID);
        modifyFilePermButt.getStylesheets().add(Main.BUTTON_STYLE);

        buttonVBox.getChildren().addAll(viewRequestsButton, modifyFilePermButt);
        buttonVBox.setAlignment(Pos.TOP_CENTER);

        overallPageVBox.getChildren().addAll(header, buttonVBox);

        overallPageVBox.setStyle("-fx-background-color: #9da5b0;");

        return overallPageVBox;
    }
}
