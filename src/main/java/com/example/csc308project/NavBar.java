package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class NavBar {
    public VBox navbarLayout() {
        VBox mainBox = new VBox(30);
        mainBox.setMaxWidth(100);
        mainBox.setMinWidth(100);
        mainBox.setAlignment(Pos.TOP_CENTER);

        mainBox.setStyle(
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: black;");

        Button viewPerm = new Button("View Permissions");
        viewPerm.setWrapText(true);
        viewPerm.setUnderline(true);
        viewPerm.setTextAlignment(TextAlignment.CENTER);

        Button manPerm = new Button("Manage Permissions");
        manPerm.setWrapText(true);
        manPerm.setUnderline(true);
        manPerm.setTextAlignment(TextAlignment.CENTER);

        Button account = new Button("Account Placeholder");
        account.setWrapText(true);
        account.setUnderline(true);
        account.setTextAlignment(TextAlignment.CENTER);

        mainBox.getChildren().addAll(viewPerm, manPerm, account);

        return mainBox;
    }
}
