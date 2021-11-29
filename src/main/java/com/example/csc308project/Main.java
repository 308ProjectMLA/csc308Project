package com.example.csc308project;

import javafx.application.Application;
import javafx.collections.ObservableList;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //static Stage stage;

    private static User currentUser;

    private static ObservableList<FileRequest> requestData;

    public static final int PAGE_WIDTH = 900;
    public static final int PAGE_HEIGHT = 700;
    public static final int WINDOW_WIDTH = PAGE_WIDTH - NavBar.BAR_WIDTH;
    public static final int SIDE_PAD = 10;
    public static final int TOP_PAD = 10;
    public static final int FIELD_WIDTH = 200;
    public static final String DATA_DIR = "data/";
    public static final String REQ_CSV = "accessRequests.csv";

    public static final String BUTTON_ID = "round-yellow";
    public static final String BUTTON_STYLE = "file:cssfiles/yellowbutton.css";
    public static final String FONT_NAME = "Times New Roman";

    @Override
    public void start(Stage primaryStage) throws IOException {
        Stage stage = primaryStage;
        stage.setTitle("MLA");

        LogInPage lip = new LogInPage();
        VBox loginBox = lip.logInPageLayout(stage);

        Scene scene = new Scene(loginBox, PAGE_WIDTH, PAGE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }

    public static void updatePage(Stage stage, VBox page, String p){
        NavBar navBar = new NavBar();
        VBox navBox = navBar.navbarLayout(stage, p);

        HBox mainBox = new HBox();

        page.setMinWidth(WINDOW_WIDTH);

        mainBox.getChildren().addAll(navBox, page);
        stage.setScene(new Scene(mainBox, PAGE_WIDTH, PAGE_HEIGHT));
    }

    public static void updateTitle(Stage stage, String newTitle){
        stage.setTitle("MLA: " + newTitle);
    }

    public static void main(String[] args) {
        launch();
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static void setCurrentUser(User user){
        currentUser = user;
    }

    public static ObservableList<FileRequest> getRequestData() {
        return requestData;
    }
    public static void setRequestData( ObservableList<FileRequest> newRequestData) {
        requestData = newRequestData;
    }

    public static void addRequestToData(FileRequest request) {
        requestData.add(request);
    }
    public static void removeRequestFromData(FileRequest request) {
        requestData.remove(request);
    }
}