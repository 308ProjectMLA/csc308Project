import com.example.csc308project.Main;
import com.example.csc308project.User;
import com.example.csc308project.ViewFileController;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ViewFileControllerTest {

    private static final String TEST_FILE = "testData/testVFC.mnf";
    private static final String TEST_FILE_CP = "testVFC" + ".mnf";

    @Test
    public void testAllowEdit() throws IOException {
        Files.copy(Paths.get(TEST_FILE), Paths.get(Main.DATA_DIR + TEST_FILE_CP));

        Main.setCurrentUser(new User("admin"));

        assertTrue(ViewFileController.allowEdit(TEST_FILE_CP.replace(".mnf", ".txt")));

        Files.delete(Paths.get(Main.DATA_DIR + TEST_FILE_CP));
    }

    @Test
    public void testDontAllowEdit() throws IOException {
        Files.copy(Paths.get(TEST_FILE), Paths.get(Main.DATA_DIR + TEST_FILE_CP));

        Main.setCurrentUser(new User("bob"));

        assertFalse(ViewFileController.allowEdit(TEST_FILE_CP.replace(".mnf", ".txt")));

        Files.delete(Paths.get(Main.DATA_DIR + TEST_FILE_CP));
    }
}
