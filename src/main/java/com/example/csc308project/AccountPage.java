package com.example.csc308project;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AccountPage {

    public VBox accountPageLayout(String username) {
        VBox mainBox = new VBox(10);
        mainBox.setAlignment(Pos.CENTER);

        Label welcomeText = new Label("Welcome " + username + "!");

        VBox groupInfo = new VBox();
        groupInfo.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");
        groupInfo.setMaxWidth(200);

        Label groupText = new Label("Groups:");

        // Hard-coded for now
        Label displayGroups = new Label("\tSupervisors\n\tSecurity Level 4");

        groupInfo.getChildren().addAll(groupText, displayGroups);

        Button logout = new Button("Logout");
        logout.setMinWidth(50);
        logout.setUnderline(true);
        logout.setOnAction(actionEvent -> {
            Platform.exit();
        });

        mainBox.getChildren().addAll(welcomeText, groupInfo, logout);
        return mainBox;
    }
}
