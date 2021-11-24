package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.*;
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

    public VBox logInPageLayout() {
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.TOP_CENTER);

        multi = new Label("Multi-Level Authorization Manager");
        multi.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 20));
        multi.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, Main.TOP_PAD, Main.SIDE_PAD));

        login = new Label("Log-in");
        login.setFont(Font.font("", FontWeight.NORMAL, FontPosture.REGULAR, 20));
        // Padding order: Top, right, bottom, left
        login.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, 200, Main.SIDE_PAD));

        testuser = new Label("test username : admin");
        testpass = new Label("test password : 1234");
        error = new Label();

        username = new TextField();
        username.setPromptText("Enter username");
        username.setMaxWidth(Main.FIELD_WIDTH);

        password = new PasswordField();
        password.setPromptText("Enter password");
        password.setMaxWidth(Main.FIELD_WIDTH);

        submit = new Button("Submit");
        submit.setDefaultButton(true);
        submit.setOnAction(actionEvent -> {
            try {
                if(isValid(User.parseUserInfo(), username.getText(), password.getText())){
                    Main.currentUser = new User(username.getText(), password.getText());
                    AccountPage ap = new AccountPage();
                    Main.updatePage(ap.accountPageLayout());
                }
                else error.setText("Username or password is incorrect. Please try again.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mainBox.getChildren().addAll(multi, login, username, password, submit, error, testuser, testpass);

        return mainBox;
    }

    // returns true if username/password combination is legitimate, false otherwise
    private boolean isValid(ArrayList<String> userinfo, String user, String pass) throws IOException {

        while (!userinfo.isEmpty()) {
            if(userinfo.get(0).compareTo(user) == 0 && userinfo.get(1).compareTo(pass) == 0) return true;
            userinfo.remove(0);
            userinfo.remove(0);
        }

        return false;
    }
}
