package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class LogInPage {

    private Label multi;
    private Label login;
    private Label testuser;
    private Label testpass;
    private Button submit;
    private Label error;
    private TextField username;
    private PasswordField password;

    ArrayList<String[]> possCombos;

    public VBox logInPageLayout(){
        VBox mainBox = new VBox(10);
        mainBox.setAlignment(Pos.TOP_CENTER);

        possCombos = new ArrayList<String[]>();
        possCombos.add(new String[]{"admin", "1234"});
        possCombos.add(new String[]{"bob", "thebuilder"});
        //possCombos.add(new String[]{"", ""});


        multi = new Label("Multi-Level Authorization Manager");
        multi.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        multi.setPadding(new Insets(35, 10, 5, 10));

        login = new Label("Log-in");
        login.setFont(Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        // Padding order: Top, left, bottom, right
        login.setPadding(new Insets(15, 10, 200, 10));

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
                Main.updatePage(ap.accountPageLayout("Admin"));
            }
            else error.setText("Username or password is incorrect. Please try again.");
        });

        mainBox.getChildren().addAll(multi, login, username, password, submit, error, testuser, testpass);

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
