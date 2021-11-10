package com.example.csc308project;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LogInPage {

    private Label multi;
    private Label testuser;
    private Label testpass;
    private Button submit;
    private Label error;
    private TextField username;
    private PasswordField password;

    ArrayList<String[]> possCombos;

    public VBox logInPageLayout(Stage primaryStage){
        VBox mainBox = new VBox(5);
        mainBox.setMinWidth(700);
        mainBox.setAlignment(Pos.CENTER);

        possCombos = new ArrayList<String[]>();
        possCombos.add(new String[]{"admin", "1234"});
        possCombos.add(new String[]{"bob", "thebuilder"});

        multi = new Label("Multi-Level Authorization Manager");
        multi.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));

        testuser = new Label("test username : admin");
        testpass = new Label("test password : 1234");
        error = new Label();

        username  = new TextField();
        username.setPromptText("Enter username");
        username.setMaxWidth(200);

        password = new PasswordField();
        password.setPromptText("Enter password");
        password.setMaxWidth(200);

        submit = new Button("Submit");
        submit.setDefaultButton(true);
        submit.setOnAction(actionEvent -> {
            if(isValid(possCombos, username.getCharacters().toString(), password.getCharacters().toString())){
                AccountPage ap = new AccountPage();
                Main.updatePage(primaryStage, ap.accountPageLayout("Admin"));
            }
            else error.setText("Username or password is incorrect. Please try again.");
        });

        mainBox.getChildren().addAll(multi, username, password, submit, error, testuser, testpass);
        return mainBox;
    }

    // returns true if username/password combination is legitimate, false otherwise
    private boolean isValid(ArrayList<String[]> combos, String user, String pass){
        for (String[] combo : combos) {
            if (combo[0].compareTo(user) == 0 && combo[1].compareTo(pass) == 0) return true;
        }
        return false;
    }
}
