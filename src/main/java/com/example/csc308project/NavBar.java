package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class NavBar {
    public VBox navbarLayout(Stage primaryStage) {
        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(100);
        mainBox.setMinWidth(100);
        mainBox.setAlignment(Pos.TOP_CENTER);

        mainBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");

        Button viewPerm = new Button("View Permissions");
        viewPerm.setWrapText(true);
        viewPerm.setUnderline(true);
        viewPerm.setTextAlignment(TextAlignment.CENTER);

        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button managePermissionButton = new Button("Manage Permissions");
        managePermissionButton.setOnAction(actionEvent -> {
            HelloApplication.updatePage(primaryStage, managePermissionPage.pageLayout(primaryStage));
        });

        managePermissionButton.setWrapText(true);
        managePermissionButton.setUnderline(true);
        managePermissionButton.setTextAlignment(TextAlignment.CENTER);

        Button account = new Button("Account Placeholder");
        account.setWrapText(true);
        account.setUnderline(true);
        account.setTextAlignment(TextAlignment.CENTER);

        mainBox.getChildren().addAll(viewPerm, managePermissionButton, account);

        return mainBox;
    }
}
