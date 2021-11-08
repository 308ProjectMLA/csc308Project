package com.example.csc308project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("MLA");
        HBox app = new HBox(5);
        VBox mainVBox = new VBox(5);
        mainVBox.setMinWidth(400);
        mainVBox.setAlignment(Pos.CENTER);

        NavBar navbar = new NavBar();
        VBox navBox = navbar.navbarLayout();

        LogInPage lip = new LogInPage();
        VBox loginBox = lip.logInPageLayout();

        Button submit = new Button("Submit");
        loginBox.getChildren().add(3, submit);
        submit.setOnAction(actionEvent -> {
            if(isValid(lip.getPossCombos(), lip.getUsername().getCharacters().toString(), lip.getPassword().getCharacters().toString())){
                HBox main = new HBox();

                main.getChildren().addAll(navBox, mainVBox);
                stage.setScene(new Scene(main, 500, 300));
            }
            else lip.getError().setText("Username or password is incorrect. Please try again.");
        });

        Button uselessButton = new Button("Useless button");

        /*TestPage tp = new TestPage();
        Button changeScreen = new Button("Other screen");
        changeScreen.setOnAction(actionEvent -> {
            stage.setScene(new Scene(tp.testLayout(stage), 500, 300));
        }); */

        AccountPage ap = new AccountPage();
        Button changeScreen = new Button("Other screen");
        changeScreen.setOnAction(actionEvent -> {
            HBox account = new HBox();
            VBox temp = ap.accountPageLayout("Bob");
            temp.setMinWidth(400);
            temp.setAlignment(Pos.CENTER);

            account.getChildren().addAll(navBox, temp);
            stage.setScene(new Scene(account, 500, 300));
        });

        ManagePermissionPage managePermissionPage = new ManagePermissionPage();
        Button managePermissionButton = new Button("Manage Permissions");
        managePermissionButton.setOnAction(actionEvent -> {
            HBox manage = new HBox();
            VBox temp = managePermissionPage.pageLayout(stage);
            temp.setMinWidth(400);
            temp.setAlignment(Pos.CENTER);

            manage.getChildren().addAll(navBox, temp);
            stage.setScene(new Scene(manage, 500, 300));
        });
        mainVBox.getChildren().addAll(uselessButton, changeScreen, managePermissionButton);
        app.getChildren().addAll(loginBox);
        Scene scene = new Scene(app, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    // returns true if username/password combination is legitimate, false otherwise
    private boolean isValid(ArrayList<String[]> combos, String user, String pass){
        for (String[] combo : combos) {
            if (combo[0].compareTo(user) == 0 && combo[1].compareTo(pass) == 0) return true;
        }
        return false;
    }
}