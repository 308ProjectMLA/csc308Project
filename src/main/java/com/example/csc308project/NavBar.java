package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class NavBar {
    public VBox navbarLayout() {
        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(100);
        mainBox.setMinWidth(100);
        mainBox.setAlignment(Pos.TOP_CENTER);

        mainBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");

        Button viewFiles = new Button("View\nFiles");
        viewFiles.setWrapText(true);
        viewFiles.setTextAlignment(TextAlignment.CENTER);
        viewFiles.setMinWidth(95);
        viewFiles.setMinHeight(45);
        viewFiles.setOnAction(actionEvent -> {
            FileSelectPage fp = new FileSelectPage();
            Main.updatePage(fp.fileSelectLayout());
        });

        Button managePermissionButton = new Button("Manage Permissions");
        managePermissionButton.setWrapText(true);
        managePermissionButton.setTextAlignment(TextAlignment.CENTER);
        managePermissionButton.setMinWidth(95);
        managePermissionButton.setMinHeight(45);
        managePermissionButton.setOnAction(actionEvent -> {
            ManagePermissionPage managePermissionPage = new ManagePermissionPage();
            Main.updatePage(managePermissionPage.pageLayout());
        });

        Button account = new Button("Account");
        account.setWrapText(true);
        account.setTextAlignment(TextAlignment.CENTER);
        account.setMinWidth(95);
        account.setMinHeight(45);
        account.setOnAction(actionEvent -> {
            AccountPage ap = new AccountPage();
            Main.updatePage(ap.accountPageLayout("Admin"));
        });

        mainBox.getChildren().addAll(viewFiles, managePermissionButton, account);

        return mainBox;
    }
}
