package com.example.csc308project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private Label error;
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
        error = new Label();

        username  = new PasswordField();
        username.setPromptText("Enter username");
        username.setMaxWidth(200);

        password = new PasswordField();
        password.setPromptText("Enter password");
        password.setMaxWidth(200);

        mainBox.getChildren().addAll(multi, username, password, error, testuser, testpass);
        return mainBox;
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
