package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AccountPage {
    private Button logout;
    private Label welcomeText;
    private Label groupText;
    private Label displayGroups;

    public VBox accountPageLayout(Stage stage, String username) {
        VBox mainBox = new VBox(10);
        mainBox.setAlignment(Pos.CENTER);

        welcomeText = new Label("Welcome " + username + "!");

        VBox groupInfo = new VBox();
        groupInfo.setStyle("-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");
        groupInfo.setMaxWidth(200);

        groupText = new Label("Groups:");

        // Hard-coded for now
        displayGroups = new Label("\tSupervisors\n\tSecurity Level 4");

        groupInfo.getChildren().addAll(groupText, displayGroups);

        logout = new Button("Logout");
        logout.setMinWidth(50);
        logout.setUnderline(true);

        mainBox.getChildren().addAll(welcomeText, groupInfo, logout);
        return mainBox;
    }
}
