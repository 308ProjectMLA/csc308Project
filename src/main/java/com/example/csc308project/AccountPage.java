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
import javafx.stage.Stage;

public class AccountPage {

    public VBox accountPageLayout(Stage stage) {
        Main.updateTitle(stage,"My Account");
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, Main.TOP_PAD, Main.SIDE_PAD));

        Label welcomeText = new Label("Welcome " + Main.getCurrentUser().getUsername() + "!");
        welcomeText.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        welcomeText.setPadding(new Insets(0, Main.SIDE_PAD, 30, Main.SIDE_PAD));

        // Hard-coded for now
        TreeItem<String> groupTree = new TreeItem<>("Groups");
        groupTree.setExpanded(true);
        for (String group : Main.getCurrentUser().groups) {
            groupTree.getChildren().add(new TreeItem<>(group));
        }

        TreeView<String> tree = new TreeView<>(groupTree);
        tree.setMinHeight(Main.PAGE_HEIGHT - 200);

        Button logout = new Button("Logout");
        logout.setMinWidth(50);
        logout.setOnAction(actionEvent -> {
            LogInPage lip = new LogInPage();
            Main.updatePage(stage, lip.logInPageLayout(stage), "account");

            VBox loginBox = lip.logInPageLayout(stage);
            Scene scene = new Scene(loginBox, Main.PAGE_WIDTH, Main.PAGE_HEIGHT);
            Main.updateTitle(stage,"Login");
            stage.setScene(scene);
        });

        logout.setId(Main.BUTTON_ID);
        logout.getStylesheets().add(Main.BUTTON_STYLE);

        mainBox.getChildren().addAll(welcomeText, tree, logout);

        mainBox.setStyle("-fx-background-color: #9da5b0;");

        return mainBox;
    }
}
