package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;

public class AccountPage {

    public VBox accountPageLayout(String username) {
        VBox mainBox = new VBox(15);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(0, 50, 0, 50));

        Label welcomeText = new Label("Welcome " + username + "!");

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
        return mainBox;
    }
}
