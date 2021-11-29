import com.example.csc308project.FileSelectController;
import com.example.csc308project.Main;
import com.example.csc308project.User;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FileSelectControllerTest {

    private static final String TEST_DIR = "testData/";

    @Test
    public void testDeleteFile() throws IOException {
        String testFile = "testFS";
        Files.createFile(Paths.get(Main.DATA_DIR + testFile + ".txt"));
        Files.createFile(Paths.get(Main.DATA_DIR + testFile + ".mnf"));

        FileSelectController.deleteFile(testFile + ".txt");


        assertFalse(Files.exists(Paths.get(Main.DATA_DIR + testFile + ".txt")));
        assertFalse(Files.exists(Paths.get(Main.DATA_DIR + testFile + ".mnf")));
    }

    @Test
    public void testAllowView() {
        final String testFile = TEST_DIR + "testAllowView";

        Main.currentUser = new User("admin");

        System.out.println(Main.currentUser.groups.get(0));

        assertTrue(FileSelectController.allowView(testFile));

        Main.currentUser = new User("testyAdmin");

        assertTrue(FileSelectController.allowView(testFile));

        Main.currentUser = new User("bob");

        assertFalse(FileSelectController.allowView(testFile));

    }

}
