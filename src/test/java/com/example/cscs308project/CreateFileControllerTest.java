package com.example.cscs308project;

import com.example.csc308project.CreateFileController;
import com.example.csc308project.Main;
import com.example.csc308project.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class CreateFileControllerTest {

    private static final String TEST_FILE = "testCF";

    @Test
    public void testCreateFile() throws IOException {
        Main.setCurrentUser(new User("admin"));

        assertTrue(CreateFileController.createFile(TEST_FILE));

        String baseFile = Main.DATA_DIR + TEST_FILE;

        assertTrue(Files.exists(Paths.get(baseFile + ".txt")));
        assertTrue(Files.exists(Paths.get(baseFile + ".mnf")));

        Files.delete(Paths.get(baseFile+ ".txt"));
        Files.delete(Paths.get(baseFile+ ".mnf"));
    }

    @Test
    public void testDontCreateFile() throws IOException {
        Main.setCurrentUser(new User("admin"));

        assertFalse(CreateFileController.createFile("bobFile"));
    }
}
