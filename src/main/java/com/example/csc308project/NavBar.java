package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class NavBar {

    public static final int BAR_WIDTH = 100;

    private static final int BUTTON_SIZE = 45;

    public String page;

    public VBox navbarLayout(String p) {
        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(BAR_WIDTH);
        mainBox.setMinWidth(BAR_WIDTH);
        mainBox.setAlignment(Pos.TOP_CENTER);
        page = p;

        mainBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-color: black;");

        Button viewFiles = new Button("View\nFiles");
        viewFiles.setWrapText(true);
        viewFiles.setTextAlignment(TextAlignment.CENTER);
        viewFiles.setMinWidth(BAR_WIDTH - 5);
        viewFiles.setMinHeight(BUTTON_SIZE);
        viewFiles.setFocusTraversable(false);
        viewFiles.setOnAction(actionEvent -> {
            FileSelectPage fp = new FileSelectPage();
            Main.updatePage(fp.fileSelectLayout(), "viewFiles");
        });
        if(page.equals("viewFiles")) {
            viewFiles.setStyle("-fx-border-color: #1ca7d7");
        }

        Button managePermissionButton = new Button("Manage Permissions");
        managePermissionButton.setWrapText(true);
        managePermissionButton.setTextAlignment(TextAlignment.CENTER);
        managePermissionButton.setMinWidth(BAR_WIDTH - 5);
        managePermissionButton.setMinHeight(BUTTON_SIZE);
        managePermissionButton.setFocusTraversable(false);
        managePermissionButton.setOnAction(actionEvent -> {
            ManagePermissionPage managePermissionPage = new ManagePermissionPage();
            Main.updatePage(managePermissionPage.pageLayout(), "managePermissions");
        });
        if(page.equals("managePermissions")) {
            managePermissionButton.setStyle("-fx-border-color: #1ca7d7");
        }

        Button account = new Button("Account");
        account.setWrapText(true);
        account.setTextAlignment(TextAlignment.CENTER);
        account.setMinWidth(BAR_WIDTH - 5);
        account.setMinHeight(BUTTON_SIZE);
        account.setFocusTraversable(false);
        account.setOnAction(actionEvent -> {
            AccountPage ap = new AccountPage();
            Main.updatePage(ap.accountPageLayout(), "account");
        });
        if(page.equals("account")) {
            account.setStyle("-fx-border-color: #1ca7d7");
        }

        mainBox.getChildren().addAll(viewFiles, managePermissionButton, account);

        return mainBox;
    }
}
