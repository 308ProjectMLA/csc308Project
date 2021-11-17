package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;

import java.io.*;

public class EditFilePage {

    Label nowviewing;
    Label file;
    Button back;
    Button viewperm;
    Button backToView;
    TextArea contents;

    public VBox editFilePageLayout(String filename, String fileContent){
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.CENTER);
        Main.stage.setTitle("Edit a File");

        nowviewing = new Label("You are now editing: ");
        nowviewing.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        nowviewing.setTextAlignment(TextAlignment.LEFT);

        file = new Label(filename);
        file.setFont(Font.font("", FontWeight.NORMAL, FontPosture.ITALIC, 20));
        file.setTextAlignment(TextAlignment.JUSTIFY);

        back = new Button("Back to File Selection");
        back.setOnAction(actionEvent -> {
            FileSelectPage fp = new FileSelectPage();
            Main.updatePage(fp.fileSelectLayout());
        });

        backToView = new Button("Back to View File");
        backToView.setOnAction(actionEvent -> {
            ViewFilePage vfp = new ViewFilePage();
            Main.updatePage(vfp.viewFilePageLayout(filename));
        });

        // TODO view permissions page
        viewperm = new Button("View Permissions");
        viewperm.setOnAction(actionEvent -> {
            System.out.println("This button does nothing yet.");
        });

        contents = new TextArea();

        contents.appendText(fileContent);

        contents.setMinHeight(400);
        contents.setMaxWidth(700);

        //TODO actually edit the file and save changes blah blah blah

        mainBox.getChildren().addAll(nowviewing, file, back, backToView, viewperm, contents);

        //arranging elements
        mainBox.getChildren().get(0).setTranslateY(-50);
        mainBox.getChildren().get(0).setTranslateX(-279);
        mainBox.getChildren().get(1).setTranslateY(-50);
        mainBox.getChildren().get(1).setTranslateX(-360 + 5 * (file.getText().length() - 5));
        mainBox.getChildren().get(2).setTranslateY(-25);
        mainBox.getChildren().get(2).setTranslateX(-315);
        mainBox.getChildren().get(3).setTranslateY(-55);
        mainBox.getChildren().get(3).setTranslateX(-180);
        mainBox.getChildren().get(4).setTranslateY(-85);
        mainBox.getChildren().get(4).setTranslateX(250);
        mainBox.getChildren().get(5).setTranslateY(-55);
        mainBox.getChildren().get(5).setTranslateX(-30);

        return mainBox;
    }
}
