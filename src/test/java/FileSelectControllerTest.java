import com.example.csc308project.FileSelectController;
import com.example.csc308project.Main;
import com.example.csc308project.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileSelectControllerTest {

    private static final String TEST_DIR = "testData/";

    @Test
    public void testDeleteTxtFile() throws IOException {
        String testTextFile = "testTextCFS";
        Files.createFile(Paths.get(Main.DATA_DIR + testTextFile + ".txt"));
        Files.createFile(Paths.get(Main.DATA_DIR + testTextFile + ".mnf"));

        FileSelectController.deleteFile(testTextFile + ".txt");


        assertFalse(Files.exists(Paths.get(Main.DATA_DIR + testTextFile + ".txt")));
    }

    @Test
    public void testDeleteMnfFile() throws IOException {
        String testMnfFile = "testFS";
        Files.createFile(Paths.get(Main.DATA_DIR + testMnfFile + ".txt"));
        Files.createFile(Paths.get(Main.DATA_DIR + testMnfFile + ".mnf"));

        FileSelectController.deleteFile(testMnfFile + ".txt");

        assertFalse(Files.exists(Paths.get(Main.DATA_DIR + testMnfFile + ".mnf")));
    }

    @Test
    public void testAllowView() throws IOException {
        final String testFile = TEST_DIR + "testAllowView";
        final String testFile2 = Main.DATA_DIR + "testAllowView";
        final String testFile2txt = "testAllowView.txt";

        Files.copy(Paths.get(testFile + ".mnf"), Paths.get(testFile2 + ".mnf"));

        Main.setCurrentUser(new User("admin"));
        assertTrue(FileSelectController.allowView(testFile2txt));

        Files.delete(Paths.get(testFile2 + ".mnf"));
    }

    @Test
    public void testNotAllowView() throws IOException {
        final String testFile = TEST_DIR + "testAllowView";
        final String testFile2 = Main.DATA_DIR + "testAllowView";
        final String testFile2txt = "testAllowView.txt";

        Files.copy(Paths.get(testFile + ".mnf"), Paths.get(testFile2 + ".mnf"));

        Main.setCurrentUser(new User("bob"));
        assertFalse(FileSelectController.allowView(testFile2txt));

        Files.delete(Paths.get(testFile2 + ".mnf"));
    }

}
