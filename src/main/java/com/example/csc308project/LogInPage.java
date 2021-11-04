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

public class LogInPage extends Application {

    Label multi;
    Label testuser;
    Label testpass;
    Label user;
    Label pass;
    Label error;
    Button submit;
    TextField username;
    TextField password;

    ArrayList<String[]> possCombos;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        stage.setTitle("Multi-Level Authorization Manager - Login");
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);

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

        submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if(isValid(possCombos, username.getCharacters().toString(), password.getCharacters().toString()))
                    error.setText("Welcome " + username.getCharacters().toString() + "! Logging you in...");
                else error.setText("Username or password is incorrect. Please try again.");
            }
        });

        root.getChildren().addAll(multi, user, username, pass, password, submit, error, testuser, testpass);
        stage.show();
    }

    // returns true if username/password combination is legitimate, false otherwise
    private boolean isValid(ArrayList<String[]> combos, String user, String pass){
        for (String[] combo : combos) {
            if (combo[0].compareTo(user) == 0 && combo[1].compareTo(pass) == 0) return true;
        }
        return false;
    }
}
