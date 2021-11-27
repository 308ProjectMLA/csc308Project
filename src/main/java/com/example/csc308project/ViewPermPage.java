package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class ViewPermPage {
    public VBox viewPermLayout(String filename) {
        Main.updateTitle("View Permissions: " + filename);
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, Main.TOP_PAD, Main.SIDE_PAD));

        Label welcomeText = new Label("Viewing Permissions for " + filename);
        welcomeText.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        welcomeText.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, 30, Main.SIDE_PAD));

        String rawFileName = filename.replace(".txt", "");

        ViewPermController vpc = null;
        try {
            vpc = new ViewPermController(rawFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert vpc != null;

        // Read tree
        TreeItem<String> readTree = new TreeItem<>("Read");
        readTree.setExpanded(true);

        TreeItem<String> rUser = new TreeItem<>("Users");
        for (String name : vpc.getReadUsers()) {
            rUser.getChildren().add(new TreeItem<>(name));
        }

        TreeItem<String> rGroup = new TreeItem<>("Groups");
        for (String name : vpc.getReadGroups()) {
            rGroup.getChildren().add(new TreeItem<>(name));
        }

        readTree.getChildren().add(rUser);
        readTree.getChildren().add(rGroup);

        // Write tree
        TreeItem<String> writeTree = new TreeItem<>("Write");
        writeTree.setExpanded(true);

        TreeItem<String> wUser = new TreeItem<>("Users");
        for (String name : vpc.getWriteUsers()) {
            wUser.getChildren().add(new TreeItem<>(name));
        }

        TreeItem<String> wGroup = new TreeItem<>("Groups");
        for (String name : vpc.getWriteGroups()) {
            wGroup.getChildren().add(new TreeItem<>(name));
        }

        writeTree.getChildren().add(wUser);
        writeTree.getChildren().add(wGroup);

        TreeView<String> treeR = new TreeView<>(readTree);
        TreeView<String> treeW = new TreeView<>(writeTree);

        // End tree shenanigans

        Button back = new Button("Back");
        back.setMinWidth(50);
        back.setOnAction(actionEvent -> {
            ViewFilePage vp = new ViewFilePage();
            Main.updatePage(vp.viewFilePageLayout(filename), FileSelectPage.PAGE_NAME);
        });

        back.setId("round-yellow");
        back.getStylesheets().add("file:cssfiles/yellowbutton.css");

        mainBox.getChildren().addAll(welcomeText, treeR, treeW, back);
        mainBox.setStyle("-fx-background-color: #9da5b0;");

        return mainBox;
    }
}
