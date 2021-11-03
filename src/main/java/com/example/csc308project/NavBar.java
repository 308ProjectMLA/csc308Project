package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavBar {
    public VBox navbarLayout() {
        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(100);
        mainBox.setAlignment(Pos.CENTER);

        mainBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");

        Button viewPerm = new Button("View Permissions");
        viewPerm.setWrapText(true);
        viewPerm.setUnderline(true);

        Button manPerm = new Button("Manage Permissions");
        manPerm.setWrapText(true);
        manPerm.setUnderline(true);

        Button account = new Button("Account Placeholder");
        account.setWrapText(true);
        account.setUnderline(true);

        mainBox.getChildren().addAll(viewPerm, manPerm, account);

        return mainBox;
    }
}
