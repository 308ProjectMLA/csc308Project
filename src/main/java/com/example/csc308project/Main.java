package com.example.csc308project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("MLA");
        HBox app = new HBox(5);
        VBox mainVBox = new VBox(5);
        mainVBox.setMinWidth(400);
        mainVBox.setAlignment(Pos.CENTER);

        LogInPage lip = new LogInPage();
        VBox loginBox = lip.logInPageLayout();

        app.getChildren().addAll(loginBox);
        Scene scene = new Scene(app, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void updatePage(VBox page){
        NavBar navBar = new NavBar();
        VBox navBox = navBar.navbarLayout();

        HBox mainBox = new HBox();
        page.setMinWidth(400);
        page.setAlignment(Pos.CENTER);

        mainBox.getChildren().addAll(navBox, page);
        stage.setScene(new Scene(mainBox, 500, 300));

    }

    public static void main(String[] args) {
        launch();
    }
}