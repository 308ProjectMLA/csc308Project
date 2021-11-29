package com.example.csc308project;

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
import javafx.stage.Stage;

public class NavBar {

    public static final int BAR_WIDTH = 100;

    private static final int BUTTON_SIZE = 45;
    private static final String BORDER_COLOR = "-fx-border-color: #1ca7d7";
    private static final String NAV_BUTT_ID = "reg-yellow";

    public VBox navbarLayout(Stage stage, String p) {
        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(BAR_WIDTH);
        mainBox.setMinWidth(BAR_WIDTH);
        mainBox.setAlignment(Pos.TOP_CENTER);

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
            Main.updatePage(stage, fp.fileSelectLayout(stage), FileSelectPage.PAGE_NAME);
            Main.updateTitle(stage, "View Files");
        });

        if(p.equals(FileSelectPage.PAGE_NAME)) {
            viewFiles.setStyle(BORDER_COLOR);
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
            Main.updatePage(stage, managePermissionPage.pageLayout(stage), "managePermissions");
            Main.updateTitle(stage, "Manage Permissions");
        });

        if(p.equals("managePermissions")) {
            managePermissionButton.setStyle(BORDER_COLOR);
        }
        managePermissionButton.setGraphic(lockView);
        managePermissionButton.setContentDisplay(ContentDisplay.TOP);
        if (!GroupController.isSupervisor(Main.getCurrentUser().getUsername())) {
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
            Main.updatePage(stage, ap.accountPageLayout(stage), "account");
            Main.updateTitle(stage, "My Account");
        });

        if(p.equals("account")) {
            account.setStyle(BORDER_COLOR);
        }

        account.setGraphic(userView);
        account.setContentDisplay(ContentDisplay.TOP);

        Text mla = new Text("MLA");

        mla.setFont(Font.font(Main.FONT_NAME, FontWeight.BOLD, 42));
        mla.setFill(Color.WHITE);

        viewFiles.setId(NAV_BUTT_ID);
        viewFiles.getStylesheets().add(Main.BUTTON_STYLE);
        managePermissionButton.setId(NAV_BUTT_ID);
        managePermissionButton.getStylesheets().add(Main.BUTTON_STYLE);
        account.setId(NAV_BUTT_ID);
        account.getStylesheets().add(Main.BUTTON_STYLE);


        mainBox.getChildren().addAll(viewFiles, managePermissionButton, account, mla);

        return mainBox;
    }
}
