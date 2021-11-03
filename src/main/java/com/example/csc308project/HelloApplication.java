package com.example.csc308project;

import javafx.application.Application;
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

        mainVBox.getChildren().addAll(uselessButton, changeScreen);
        app.getChildren().addAll(navBox, mainVBox);
        Scene scene = new Scene(app, 500, 300);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}