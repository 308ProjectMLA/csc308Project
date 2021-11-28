package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogInPage {

    private Label multi;
    private Label login;
    private Label testuser;
    private Label testpass;
    private Button submit;
    private Label error;
    private TextField username;
    private PasswordField password;
    private Canvas background;

    public VBox logInPageLayout() {
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.TOP_CENTER);
        Main.updateTitle("Login");

        background = new Canvas(900,250);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Color.rgb(52,95,159));
        gc.fillRect(0,0,900,100);
        gc.setFill(Color.rgb(255,255,255));
        gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 80));
        gc.fillText("MLA",50,100);
        gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 32));
        gc.fillText("Multi Level Authorization Manager",250,85);

        Image userIcon = new Image("file:img/usericon.png", 120,123.9,false,false);
        gc.drawImage(userIcon,390,125);

        login = new Label("Login");
        login.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
        login.setTextFill(Color.WHITE);
        // Padding order: Top, right, bottom, left
        login.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, 100, Main.SIDE_PAD));

        error = new Label();
        error.setFont(Font.font("Times New Roman", FontWeight.BOLD, 16));
//        error.setTextFill(Color.web("#a70605"));
        error.setTextFill(Color.INDIANRED);

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
                    Main.updatePage(ap.accountPageLayout(), "account");
                }
                else error.setText("Username or password is incorrect. Please try again.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        submit.setId("round-yellow");
        submit.getStylesheets().add("file:cssfiles/yellowbutton.css");

        mainBox.setStyle("-fx-background-image: url('file:img/network-background.png');");

        mainBox.getChildren().addAll(background, login, username, password, submit, error);

        return mainBox;
    }

    // returns true if username/password combination is legitimate, false otherwise
    private boolean isValid(List<String> userinfo, String user, String pass) throws IOException {

        while (!userinfo.isEmpty()) {
            if(userinfo.get(0).compareTo(user) == 0 && userinfo.get(1).compareTo(pass) == 0) return true;
            userinfo.remove(0);
            userinfo.remove(0);
        }

        return false;
    }
}
