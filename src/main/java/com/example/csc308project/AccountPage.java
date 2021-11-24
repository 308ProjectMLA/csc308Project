package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class AccountPage {

    public VBox accountPageLayout() {
        Main.updateTitle("My Account");
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, Main.TOP_PAD, Main.SIDE_PAD));

        Label welcomeText = new Label("Welcome " + Main.currentUser.getUsername() + "!");
        welcomeText.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        welcomeText.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, 30, Main.SIDE_PAD));

        // Hard-coded for now
        TreeItem<String> groupTree = new TreeItem<>("Groups");
        groupTree.setExpanded(true);
        groupTree.getChildren().add(new TreeItem<>("Supervisors"));
        groupTree.getChildren().add(new TreeItem<>("Security Level 4"));

        TreeView<String> tree = new TreeView<>(groupTree);

        Button logout = new Button("Logout");
        logout.setMinWidth(50);
        logout.setOnAction(actionEvent -> {
            LogInPage lip = new LogInPage();
            Main.updatePage(lip.logInPageLayout());
            VBox loginBox = lip.logInPageLayout();
            Scene scene = new Scene(loginBox, Main.PAGE_WIDTH, Main.PAGE_HEIGHT);
            Main.stage.setScene(scene);
        });

        mainBox.getChildren().addAll(welcomeText, tree, logout);

        // TODO remove
        //ManifestParser mp = new ManifestParser("test");
        //mp.createDefaultManifest();
        /*try {
            mp.readManifest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mp.addPermission("group", "admin", 'w');
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mp.readManifest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mp.removePermission("group", "poodles", 'w');
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return mainBox;
    }
}
