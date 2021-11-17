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

    // TODO Use a User entity class for storing this info
    static String username;

    public static final int PAGE_WIDTH = 900;
    public static final int PAGE_HEIGHT = 700;
    public static final int WINDOW_WIDTH = PAGE_WIDTH - NavBar.BAR_WIDTH;
    public static final int SIDE_PAD = 10;
    public static final int TOP_PAD = 10;
    public static final int FIELD_WIDTH = 200;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stage.setTitle("MLA");

        LogInPage lip = new LogInPage();
        VBox loginBox = lip.logInPageLayout();

        Scene scene = new Scene(loginBox, PAGE_WIDTH, PAGE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void updatePage(VBox page){
        NavBar navBar = new NavBar();
        VBox navBox = navBar.navbarLayout();

        HBox mainBox = new HBox();
        page.setMinWidth(WINDOW_WIDTH);
        page.setAlignment(Pos.CENTER);

        mainBox.getChildren().addAll(navBox, page);
        stage.setScene(new Scene(mainBox, PAGE_WIDTH, PAGE_HEIGHT));
    }
    public static void updateTitle(String newTitle){
        stage.setTitle("MLA: " + newTitle);
    }

    public static void main(String[] args) {
        launch();
    }
}