package com.example.csc308project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class LogInPage {

    private Label multi;
    private Label testuser;
    private Label testpass;
    private Label user;
    private Label pass;
    private Label error;
    private Button submit;
    private TextField username;
    private TextField password;

    ArrayList<String[]> possCombos;

    public VBox logInPageLayout(){
        VBox mainBox = new VBox(5);
        mainBox.setMinWidth(500);
        mainBox.setAlignment(Pos.CENTER);

        possCombos = new ArrayList<String[]>();
        possCombos.add(new String[]{"admin", "1234"});
        possCombos.add(new String[]{"bob", "thebuilder"});


        multi = new Label("Multi-Level Authorization Manager");
        multi.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));

        testuser = new Label("test username : admin");
        testpass = new Label("test password : 1234");
        user = new Label("username");
        pass = new Label("password ");
        error = new Label();

        username  = new TextField();
        username.setMaxWidth(200);

        password = new TextField();
        password.setMaxWidth(200);

//        submit = new Button("Submit");
//        submit.setOnAction(new EventHandler<ActionEvent>() {
//
//            public void handle(ActionEvent event) {
//                if(isValid(possCombos, username.getCharacters().toString(), password.getCharacters().toString()))
//                    error.setText("Welcome " + username.getCharacters().toString() + "! Logging you in...");
//                else error.setText("Username or password is incorrect. Please try again.");
//            }
//        });

        mainBox.getChildren().addAll(multi, user, username, pass, password, error, testuser, testpass);
        return mainBox;
    }

    // returns true if username/password combination is legitimate, false otherwise
    private boolean isValid(ArrayList<String[]> combos, String user, String pass){
        for (String[] combo : combos) {
            if (combo[0].compareTo(user) == 0 && combo[1].compareTo(pass) == 0) return true;
        }
        return false;
    }

    public Label getError() {
        return error;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public ArrayList<String[]> getPossCombos() {
        return possCombos;
    }
}
