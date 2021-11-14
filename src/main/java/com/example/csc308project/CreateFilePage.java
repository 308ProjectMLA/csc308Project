package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;

public class CreateFilePage {
    Button backButton;
    Button createButton;
    private TextField fileName;
    boolean result;

    public VBox CreateFileLayout() {
        Main.updateTitle("Create New File");
        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);

        //get name for new file
        fileName  = new TextField();
        fileName.setPromptText("Enter new file name");
        fileName.setMaxWidth(200);

        //create button
        createButton = new Button("create");
        createButton.setOnAction(actionEvent -> {
            //checks to see that there is actually text in the file name
            if(fileName.getCharacters().toString() != "" || fileName.getCharacters().toString() != "\n"){
                System.out.println(fileName.getCharacters().toString());
                //actually makes the file
                File newFile = new File("data/"+ fileName.getCharacters().toString() +".txt");
                try {
                    result = newFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                System.out.println("empty");
            }
        });

        //success message?
        //open or back option

        backButton = new Button("back");

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent -> {
            Main.updatePage(fsp.fileSelectLayout());
        });

        mainVBox.getChildren().addAll(fileName, createButton, backButton);
        return mainVBox;
    }
}
