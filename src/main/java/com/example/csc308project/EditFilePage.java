package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditFilePage {

    Label nowviewing;
    Label file;
    Button back;
    Button viewperm;
    Button backToView;
    Button save;
    TextArea contents;
    private static final Logger LOGGER = Logger.getLogger( EditFilePage.class.getName());


    public VBox editFilePageLayout(Stage stage, String filename, String fileContent){
        VBox mainBox = new VBox(30);
        mainBox.setAlignment(Pos.CENTER);
        Main.updateTitle(stage,"File Editor");

        VBox curFile = new VBox(5);
        curFile.setAlignment(Pos.TOP_LEFT);

        nowviewing = new Label("You are now editing: ");
        nowviewing.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));

        file = new Label(filename);
        file.setFont(Font.font("", FontWeight.NORMAL, FontPosture.ITALIC, 20));

        curFile.getChildren().addAll(nowviewing, file);
        curFile.setPadding(new Insets(0,0,0,30));

        HBox backButton = new HBox(5);

        back = new Button("Back to File Selection");
        back.setId(Main.BUTTON_ID);
        back.getStylesheets().add(Main.BUTTON_STYLE);
        back.setOnAction(actionEvent -> {
            FileSelectPage fp = new FileSelectPage();
            Main.updatePage(stage, fp.fileSelectLayout(stage), FileSelectPage.PAGE_NAME);
        });

        backButton.getChildren().add(back);
        backButton.setPadding(new Insets(0,355,0,0));

        HBox buttons = new HBox(5);

        backToView = new Button("Back to View File");
        backToView.setOnAction(actionEvent -> {
            ViewFilePage vfp = new ViewFilePage();
            Main.updatePage(stage, vfp.viewFilePageLayout(stage, filename), FileSelectPage.PAGE_NAME);
        });

        viewperm = new Button("View Permissions");
        viewperm.setOnAction(actionEvent -> {
            ViewPermPage pp = new ViewPermPage();
            Main.updatePage(stage, pp.viewPermLayout(stage, filename), FileSelectPage.PAGE_NAME);
        });

        VBox contentSave = new VBox(5);
        contentSave.setAlignment(Pos.CENTER_LEFT);

        contents = new TextArea();

        contents.appendText(fileContent);

        contents.setMinHeight(400);
        contents.setMaxWidth(700);

        viewperm.setId(Main.BUTTON_ID);
        viewperm.getStylesheets().add(Main.BUTTON_STYLE);
        backToView.setId(Main.BUTTON_ID);
        backToView.getStylesheets().add(Main.BUTTON_STYLE);

        buttons.getChildren().addAll(backToView, viewperm);

        HBox allButtons = new HBox(5);
        allButtons.getChildren().addAll(backButton, buttons);
        allButtons.setAlignment(Pos.CENTER_LEFT);
        allButtons.setPadding(new Insets(0,0,0,30));

        save = new Button("Save Changes to File");
        save.setOnAction(actionEvent -> {
            String filepath = Main.DATA_DIR + filename;
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filepath))){
                bw.write(contents.getText());
            } catch (Exception ignored) {
                LOGGER.log(Level.WARNING, "Exception thrown");
            }
        });

        save.setId(Main.BUTTON_ID);
        save.getStylesheets().add(Main.BUTTON_STYLE);

        contentSave.getChildren().addAll(contents, save);
        contentSave.setPadding(new Insets(0,0,0,30));

        mainBox.getChildren().addAll(curFile, allButtons, contentSave);
        mainBox.setPadding(new Insets(0,0,95,0));
        mainBox.setStyle("-fx-background-color: #9da5b0;");

        return mainBox;
    }
}
