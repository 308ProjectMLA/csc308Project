package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class NavBar {

    public static final int BAR_WIDTH = 100;

    public VBox navbarLayout() {
        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(BAR_WIDTH);
        mainBox.setMinWidth(BAR_WIDTH);
        mainBox.setAlignment(Pos.TOP_CENTER);

        mainBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-color: black;");

        Button viewFiles = new Button("View\nFiles");
        viewFiles.setWrapText(true);
        viewFiles.setTextAlignment(TextAlignment.CENTER);
        viewFiles.setMinWidth(BAR_WIDTH - 5);
        viewFiles.setMinHeight(45);
        viewFiles.setFocusTraversable(false);
        viewFiles.setOnAction(actionEvent -> {
            FileSelectPage fp = new FileSelectPage();
            Main.updatePage(fp.fileSelectLayout());
        });

        Button managePermissionButton = new Button("Manage Permissions");
        managePermissionButton.setWrapText(true);
        managePermissionButton.setTextAlignment(TextAlignment.CENTER);
        managePermissionButton.setMinWidth(BAR_WIDTH - 5);
        managePermissionButton.setMinHeight(45);
        managePermissionButton.setFocusTraversable(false);
        managePermissionButton.setOnAction(actionEvent -> {
            ManagePermissionPage managePermissionPage = new ManagePermissionPage();
            Main.updatePage(managePermissionPage.pageLayout());
        });

        Button account = new Button("Account");
        account.setWrapText(true);
        account.setTextAlignment(TextAlignment.CENTER);
        account.setMinWidth(BAR_WIDTH - 5);
        account.setMinHeight(45);
        account.setFocusTraversable(false);
        account.setOnAction(actionEvent -> {
            AccountPage ap = new AccountPage();
            Main.updatePage(ap.accountPageLayout());
        });

        mainBox.getChildren().addAll(viewFiles, managePermissionButton, account);

        return mainBox;
    }
}
