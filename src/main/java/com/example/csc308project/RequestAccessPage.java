package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestAccessPage {
    Button backButton;
    Label suc;
    Label pageTitle;
    CheckBox rButton;
    CheckBox wButton;
    Button submitButton;
    boolean requestAttempted;

    private static final Logger LOGGER = Logger.getLogger( RequestAccessPage.class.getName());

    public VBox requestAccessLayout(String fileName) {
        Main.updateTitle("Request Access");
        VBox mainVBox = new VBox(Main.TOP_PAD * 3);
        mainVBox.setAlignment(Pos.TOP_CENTER);
        ViewAccessRequestPage arp = new ViewAccessRequestPage();

        pageTitle = new Label("Request Access for: " + fileName);
        pageTitle.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        pageTitle.setPadding(new Insets(40, 0 , 200, 0 ));

        suc = new Label("");
        requestAttempted = false;

        //buttons bb (r, w, submit)

        //code for requests
        //get the stuff
        //putting stuff into the csv file
        //finding a way to get the stuff from the csv file
        //finding a way to delete the stuff(deny and approve)

        rButton = new CheckBox("Read");
        rButton.setMinWidth(150);
        rButton.setIndeterminate(false);

        wButton = new CheckBox("Read + write");
        wButton.setMinWidth(150);
        wButton.setIndeterminate(false);

        submitButton = new Button("Submit");
        submitButton.setOnAction(actionEvent -> {
            //attempt to submit the request
            //maybe put in a success message?
            try {
                FileWriter myWriter = new FileWriter(Main.DATA_DIR + "accessRequests.csv", true);
                myWriter.write(Main.getCurrentUser().getUsername() +",");
                myWriter.write(fileName +",");

                if(wButton.isSelected()) {
                    //FileRequest rec = new FileRequest(Main.currentUser.getUsername(), fileName, "w");
                    //arp.addRequestToTable(rec);
                    myWriter.write("w,");

                }else if(rButton.isSelected()) {
                    //FileRequest rec = new FileRequest(Main.currentUser.getUsername(), fileName, "r");
                    //arp.addRequestToTable(rec);
                    myWriter.write("r,");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Exception thrown");
            }

        });

        backButton = new Button("Back");

        submitButton.setId(Main.BUTTON_ID);
        submitButton.getStylesheets().add(Main.BUTTON_STYLE);
        backButton.setId(Main.BUTTON_ID);
        backButton.getStylesheets().add(Main.BUTTON_STYLE);

        FileSelectPage fsp = new FileSelectPage();
        backButton.setOnAction(actionEvent ->
            Main.updatePage(fsp.fileSelectLayout(), FileSelectPage.PAGE_NAME));

        //this will have the buttons for read and write
        HBox buttBox = new HBox(Main.SIDE_PAD * 2);
        buttBox.setAlignment(Pos.CENTER);
        buttBox.getChildren().addAll(submitButton,backButton);

        mainVBox.getChildren().addAll(pageTitle, rButton, wButton, buttBox, suc);
        mainVBox.setStyle("-fx-background-color: #9da5b0;");

        return mainVBox;
    }
}