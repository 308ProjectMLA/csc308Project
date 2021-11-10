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
        mainVBox.setMinWidth(600);
        mainVBox.setAlignment(Pos.CENTER);

        LogInPage lip = new LogInPage();
        VBox loginBox = lip.logInPageLayout(stage);

        app.getChildren().addAll(loginBox);
        Scene scene = new Scene(app, 700, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static void updatePage(Stage stage, VBox page){
        NavBar navBar = new NavBar();
        VBox navBox = navBar.navbarLayout(stage);

        HBox mainBox = new HBox();
        page.setMinWidth(600);
        page.setAlignment(Pos.CENTER);

        mainBox.getChildren().addAll(navBox, page);
        stage.setScene(new Scene(mainBox, 700, 500));

    }

    public static void main(String[] args) {
        launch();
    }
}