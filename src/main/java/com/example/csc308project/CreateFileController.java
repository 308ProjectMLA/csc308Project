package com.example.csc308project;

import java.io.File;
import java.io.IOException;

public class CreateFileController {

    public static boolean createFile(String filename) throws IOException {
        if (filename.isBlank()) {
            return false;
        }

        File newFile = new File(Main.DATA_DIR + filename + ".txt");

        if (newFile.createNewFile()) {
            ManifestParser mp = new ManifestParser(filename);
            mp.createDefaultManifest();
            return true;
        }

        return false;
    }
}
