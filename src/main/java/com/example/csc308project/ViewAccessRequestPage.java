package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewAccessRequestPage {

    public VBox pageLayout(Stage primaryStage) {
        VBox pageVBox = new VBox(20);
        HBox titleBox = new HBox(10);

        //create page title
        titleBox.setAlignment(Pos.CENTER);
        Text title = new Text("Access Request Page\t\t");

        //back button
        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button backButton = new Button("Back to Permissions Page");
        backButton.setOnAction(actionEvent -> {
            HelloApplication.updatePage(primaryStage, managePermissionPage.pageLayout(primaryStage));
        });

        titleBox.getChildren().addAll(title, backButton);

        //create page content
        Text exampleText =  new Text("\tNo current access requests for this file.");

        //create page
        pageVBox.getChildren().addAll(titleBox, exampleText);

        return pageVBox;
    }

}
