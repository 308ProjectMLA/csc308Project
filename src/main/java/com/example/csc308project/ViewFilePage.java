package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;

import java.io.*;

public class ViewFilePage {

    Label nowviewing;
    Label file;
    Button back;
    Button edit;
    Button viewperm;
    TextArea viewonly;

    public VBox viewFilePageLayout(String filename){
        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.CENTER);
        Main.updateTitle("File Viewer");

        VBox curFile = new VBox(5);
        curFile.setAlignment(Pos.TOP_LEFT);

        nowviewing = new Label("You are now viewing: ");
        nowviewing.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        nowviewing.setTextFill(Color.WHITE);

        file = new Label(filename);
        file.setFont(Font.font("", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        file.setTextFill(Color.WHITE);

        curFile.getChildren().addAll(nowviewing, file);
        curFile.setPadding(new Insets(0,0,0,30));

        HBox backButton = new HBox(5);

        back = new Button("Back to File Selection");
        back.setOnAction(actionEvent -> {
            FileSelectPage fp = new FileSelectPage();
            Main.updatePage(fp.fileSelectLayout(), FileSelectPage.PAGE_NAME);
        });

        back.setId("round-yellow");
        back.getStylesheets().add("file:cssfiles/yellowbutton.css");

        backButton.getChildren().add(back);
        backButton.setPadding(new Insets(0,400,0,0));

        HBox buttons = new HBox(5);

        edit = new Button("Edit File");

        viewperm = new Button("View Permissions");
        viewperm.setOnAction(actionEvent -> {
            ViewPermPage pp = new ViewPermPage();
            Main.updatePage(pp.viewPermLayout(filename), FileSelectPage.PAGE_NAME);
        });

        VBox fileContent = new VBox(5);

        viewonly = new TextArea();

        String filepath = Main.DATA_DIR + filename;
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String temp;
            while((temp = br.readLine()) != null){
                viewonly.appendText(temp + "\n");
            }
            if(viewonly.getText().length() > 0) viewonly.setText(viewonly.getText().substring(0, viewonly.getText().length() - 1));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        viewonly.setMinHeight(400);
        viewonly.setMaxWidth(700);
        viewonly.setEditable(false);

        edit.setOnAction(actionEvent -> {
            EditFilePage efp = new EditFilePage();
            Main.updatePage(efp.editFilePageLayout(filename, viewonly.getText()), FileSelectPage.PAGE_NAME);
        });
        if (!ViewFileController.allowEdit(filename)) {
            edit.setDisable(true);
            edit.setTooltip(new Tooltip("You do not have permission to edit this file"));
        }

        edit.setId("round-yellow");
        edit.getStylesheets().add("file:cssfiles/yellowbutton.css");
        viewperm.setId("round-yellow");
        viewperm.getStylesheets().add("file:cssfiles/yellowbutton.css");

        buttons.getChildren().addAll(edit, viewperm);

        HBox allButtons = new HBox(5);
        allButtons.getChildren().addAll(backButton, buttons);
        allButtons.setAlignment(Pos.CENTER_LEFT);
        allButtons.setPadding(new Insets(0,0,0,30));

        fileContent.getChildren().add(viewonly);
        fileContent.setAlignment(Pos.CENTER_LEFT);
        fileContent.setPadding(new Insets(0,0,0,30));

        mainBox.getChildren().addAll(curFile, allButtons, fileContent);
        mainBox.setPadding(new Insets(0,0,126,0));
        mainBox.setStyle("-fx-background-image: url('file:img/network-background.png');");

        return mainBox;
    }
}
