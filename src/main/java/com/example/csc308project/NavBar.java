package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
                "-fx-border-color: black; -fx-background-color: #345f9f;");

        Image folder = new Image("file:img/file-icon.png",64,64,false,false);
        Image lock = new Image("file:img/lockicon.png",64,64,false,false);
        Image user = new Image("file:img/usericon.png",64,64,false,false);
        ImageView folderView = new ImageView(folder);
        ImageView lockView = new ImageView(lock);
        ImageView userView = new ImageView(user);

        Button viewFiles = new Button("View Files");
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

        viewFiles.setGraphic(folderView);
        viewFiles.setContentDisplay(ContentDisplay.TOP);


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
        managePermissionButton.setGraphic(lockView);
        managePermissionButton.setContentDisplay(ContentDisplay.TOP);
        if (!Group.isSupervisor(Main.currentUser.getUsername())) {
            managePermissionButton.setDisable(true);
            managePermissionButton.setTooltip(new Tooltip("You are not a supervisor"));
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

        account.setGraphic(userView);
        account.setContentDisplay(ContentDisplay.TOP);

        Text mla = new Text("MLA");

        mla.setFont(Font.font("Times New Roman", FontWeight.BOLD, 42));
        mla.setFill(Color.WHITE);


        mainBox.getChildren().addAll(viewFiles, managePermissionButton, account, mla);

        return mainBox;
    }
}
