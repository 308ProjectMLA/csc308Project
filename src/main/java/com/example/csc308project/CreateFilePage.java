package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;

public class CreateFilePage {
    Button backButton;
    Button createButton;
    TextField fileName;
    Label suc;
    Label pageTitle;
    Label prompt;
    boolean fileCreationAttempted;

    public VBox createFileLayout() {
        Main.updateTitle("Create New File");
        VBox mainVBox = new VBox(Main.TOP_PAD);
        mainVBox.setAlignment(Pos.CENTER);

        pageTitle = new Label("Create a file");
        prompt = new Label("Enter new file name:");

        suc = new Label("");
        fileCreationAttempted = false;

        //get name for new file
        fileName  = new TextField();
        fileName.setPromptText("Enter new file name");
        fileName.setMaxWidth(Main.FIELD_WIDTH);

        //create button
        createButton = new Button("create");
        createButton.setOnAction(actionEvent -> {
            //checks to see that there is actually text in the file name
            if(fileName.getCharacters().toString().equals("")|| fileName.getCharacters().toString().equals("\n")){
                //actually makes the file
                //were only making text files rn lmao
                File newFile = new File("data/"+ fileName.getCharacters().toString() +".txt");

                ManifestParser mp = new ManifestParser(fileName.getCharacters().toString());
                mp.createDefaultManifest();

                try {
                    //success message?
                    if(newFile.createNewFile()){
                        //success bb
                        suc.setText("file creation successful");
                    }else{
                        //failed
                        suc.setText("file creation failed");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //open or back option
        backButton = new Button("back");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout());
        });

        HBox buttBox = new HBox(Main.SIDE_PAD);
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(createButton, backButton);

        mainVBox.getChildren().addAll(pageTitle, prompt, fileName, buttBox, suc);
        return mainVBox;
    }
}
