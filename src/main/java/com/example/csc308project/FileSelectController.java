package com.example.csc308project;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class FileSelectController {

    private static final int LABEL_INDEX = 1;

    // Sort buttons in the vbox alphabetically
    public static void sortButtons(ArrayList<VBox> buttons) {
        buttons.sort((vbox1, vbox2) -> {
            Label l1 = (Label) vbox1.getChildren().get(LABEL_INDEX);
            Label l2 = (Label) vbox2.getChildren().get(LABEL_INDEX);

            assert !l1.getText().isBlank() && !l2.getText().isBlank();

            return l1.getText().compareTo(l2.getText());
        });
    }

    // Gets all the files in the data directory
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

    // Gets all the permission files in the data directory
    public static ArrayList<File> getPermFiles() {
        ArrayList<File> files = new ArrayList<>();

        File dir = new File("data/");

        for (File f : Objects.requireNonNull(dir.listFiles())) {
            if (f.getName().contains(".mnf")) {
                files.add(f);
            }
        }

        return files;
    }
}
