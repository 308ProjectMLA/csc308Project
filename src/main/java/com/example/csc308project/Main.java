package com.example.csc308project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("MLA");
        HBox app = new HBox(5);
        VBox mainVBox = new VBox(5);
        mainVBox.setMinWidth(400);
        mainVBox.setAlignment(Pos.CENTER);

        LogInPage lip = new LogInPage();
        VBox loginBox = lip.logInPageLayout(stage);

        app.getChildren().addAll(loginBox);
        Scene scene = new Scene(app, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void updatePage(Stage stage, VBox page){
        NavBar navBar = new NavBar();
        VBox navBox = navBar.navbarLayout(stage);

        HBox mainBox = new HBox();
        page.setMinWidth(400);
        page.setAlignment(Pos.CENTER);

        mainBox.getChildren().addAll(navBox, page);
        stage.setScene(new Scene(mainBox, 500, 300));

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