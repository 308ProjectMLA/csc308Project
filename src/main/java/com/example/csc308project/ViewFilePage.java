package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
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
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.CENTER);
        Main.stage.setTitle("View a File");

        nowviewing = new Label("You are now viewing: ");
        nowviewing.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));

        file = new Label(filename);
        file.setFont(Font.font("", FontWeight.NORMAL, FontPosture.ITALIC, 20));

        back = new Button("Back to File Selection");
        back.setOnAction(actionEvent -> {
            FileSelectPage fp = new FileSelectPage();
            Main.updatePage(fp.fileSelectLayout());
        });

        // TODO edit file page
        edit = new Button("Edit File");

        // TODO view permissions page
        viewperm = new Button("View Permissions");
        viewperm.setOnAction(actionEvent -> {
            System.out.println("This button does nothing yet.");
        });

        viewonly = new TextArea();

        String filepath = "data/" + filename;
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            String temp;
            while((temp = br.readLine()) != null){
                viewonly.appendText(temp + "\n");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        viewonly.setMinHeight(400);
        viewonly.setMaxWidth(700);
        viewonly.setEditable(false);

        edit.setOnAction(actionEvent -> {
            EditFilePage efp = new EditFilePage();
            Main.updatePage(efp.editFilePageLayout(filename, viewonly.getText()));
        });

        mainBox.getChildren().addAll(nowviewing, file, back, edit, viewperm, viewonly);

        //arranging elements
        mainBox.getChildren().get(0).setTranslateY(-50);
        mainBox.getChildren().get(0).setTranslateX(-275);
        mainBox.getChildren().get(1).setTranslateY(-50);
        mainBox.getChildren().get(1).setTranslateX(-360 + 5 * (file.getText().length() - 5));
        mainBox.getChildren().get(2).setTranslateY(-25);
        mainBox.getChildren().get(2).setTranslateX(-315);
        mainBox.getChildren().get(3).setTranslateY(-55);
        mainBox.getChildren().get(3).setTranslateX(150);
        mainBox.getChildren().get(4).setTranslateY(-85);
        mainBox.getChildren().get(4).setTranslateX(250);
        mainBox.getChildren().get(5).setTranslateY(-55);
        mainBox.getChildren().get(5).setTranslateX(-30);

        return mainBox;
    }
}
