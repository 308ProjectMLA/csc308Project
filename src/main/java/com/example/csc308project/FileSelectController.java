package com.example.csc308project;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class FileSelectController {

    public static void sortButtons(ArrayList<VBox> buttons) {
        buttons.sort((vbox1, vbox2) -> {
            Label l1 = (Label) vbox1.getChildren().get(1);
            Label l2 = (Label) vbox2.getChildren().get(1);

            assert !l1.getText().isBlank() && !l2.getText().isBlank();

            return l1.getText().compareTo(l2.getText());
        });
    }

    public static ArrayList<File> getFiles() {
        ArrayList<File> files = new ArrayList<>();

        File dir = new File("data/");

        for (File f : Objects.requireNonNull(dir.listFiles())) {
            if (f.getName().contains(".txt")) {
                files.add(f);
            }
        }

        return files;
    }
}
