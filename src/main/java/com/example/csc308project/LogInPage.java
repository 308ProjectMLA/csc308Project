package com.example.csc308project;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogInPage {

    private static final Logger LOGGER = Logger.getLogger( LogInPage.class.getName());

    public VBox logInPageLayout(Stage stage) {
        VBox mainBox = new VBox(Main.TOP_PAD);
        mainBox.setAlignment(Pos.TOP_CENTER);
        Main.updateTitle(stage,"Login");

        Canvas background = new Canvas(900, 250);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(Color.rgb(52,95,159));
        gc.fillRect(0,0,900,100);
        gc.setFill(Color.rgb(255,255,255));
        gc.setFont(Font.font(Main.FONT_NAME, FontWeight.BOLD, 80));
        gc.fillText("MLA",50,100);
        gc.setFont(Font.font(Main.FONT_NAME, FontWeight.BOLD, 32));
        gc.fillText("Multi Level Authorization Manager",250,85);

        Image userIcon = new Image("file:img/usericon.png", 120,123.9,false,false);
        gc.drawImage(userIcon,390,125);

        Label login = new Label("Login");
        login.setFont(Font.font("", FontWeight.BOLD, FontPosture.REGULAR, 30));
        // Padding order: Top, right, bottom, left
        login.setPadding(new Insets(Main.TOP_PAD, Main.SIDE_PAD, 100, Main.SIDE_PAD));

        Label error = new Label();
        error.setFont(Font.font(Main.FONT_NAME, FontWeight.BOLD, 16));
        error.setTextFill(Color.INDIANRED);

        TextField username = new TextField();
        username.setPromptText("Enter username");
        username.setMaxWidth(Main.FIELD_WIDTH);

        PasswordField password = new PasswordField();
        password.setPromptText("Enter password");
        password.setMaxWidth(Main.FIELD_WIDTH);

        Button submit = new Button("Submit");
        submit.setDefaultButton(true);
        submit.setOnAction(actionEvent -> {
            try {
                if(isValid(UserController.parseUserInfo(), username.getText(), password.getText())){
                    Main.setCurrentUser(new User(username.getText()));
                    AccountPage ap = new AccountPage();
                    Main.updatePage(stage, ap.accountPageLayout(stage), "account");
                }
                else error.setText("Username or password is incorrect. Please try again.");
            } catch (Exception ignored) {
                LOGGER.log(Level.WARNING, "Exception thrown");
            }
        });
        submit.setId(Main.BUTTON_ID);
        submit.getStylesheets().add(Main.BUTTON_STYLE);

        mainBox.setStyle("-fx-background-color: #9da5b0;");

        mainBox.getChildren().addAll(background, login, username, password, submit, error);

        return mainBox;
    }

    // returns true if username/password combination is legitimate, false otherwise
    private boolean isValid(List<String> userinfo, String user, String pass) {

        while (!userinfo.isEmpty()) {
            if(userinfo.get(0).compareTo(user) == 0 && userinfo.get(1).compareTo(pass) == 0) return true;
            userinfo.remove(0);
            userinfo.remove(0);
        }

        return false;
    }
}
