import com.example.csc308project.Main;
import com.example.csc308project.ManifestParser;
import com.example.csc308project.User;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ManifestParserTest {

    private static final String TEST_FILE = "testMP";
    private static final String TEST_USER = "testyAdmin";
    private static final String TEST_GROUP = "writers";

    @Test
    public void testCreateDefault() throws IOException {
        Main.currentUser = new User(TEST_USER);
        ManifestParser mp = new ManifestParser(TEST_FILE);
        mp.createDefaultManifest();

        Path expected = Paths.get("testData/testCreateMan.mnf");
        Path actual = Paths.get(Main.DATA_DIR + TEST_FILE + ".mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testAddPermissionSimple() throws IOException, ParseException {
        Main.currentUser = new User(TEST_USER);
        ManifestParser mp = new ManifestParser(TEST_FILE);
        mp.createDefaultManifest();

        assertTrue(mp.addPermission(ManifestParser.USER_TAG, "larry", 'r'));
        assertTrue(mp.addPermission(ManifestParser.GROUP_TAG, TEST_GROUP, 'w'));

        Path expected = Paths.get("testData/testAddPermSimple.mnf");
        Path actual = Paths.get(Main.DATA_DIR + TEST_FILE + ".mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testAddPermissionComplex() throws IOException, ParseException {
        Main.currentUser = new User(TEST_USER);
        ManifestParser mp = new ManifestParser(TEST_FILE);
        mp.createDefaultManifest();

        assertTrue(mp.addPermission(ManifestParser.USER_TAG, "larry", 'w'));
        assertTrue(mp.addPermission(ManifestParser.USER_TAG, "larry", 'r'));
        assertTrue(mp.addPermission(ManifestParser.GROUP_TAG, TEST_GROUP, 'r'));
        assertTrue(mp.addPermission(ManifestParser.GROUP_TAG, TEST_GROUP, 'w'));

        assertFalse(mp.addPermission(ManifestParser.GROUP_TAG, TEST_GROUP, 'w'));
        assertFalse(mp.addPermission(ManifestParser.USER_TAG, TEST_USER, 'r'));


        Path expected = Paths.get("testData/testAddPermComplex.mnf");
        Path actual = Paths.get(Main.DATA_DIR + TEST_FILE + ".mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testRmPermissionSimple() throws IOException, ParseException {
        Main.currentUser = new User(TEST_USER);
        ManifestParser mp = new ManifestParser(TEST_FILE);
        mp.createDefaultManifest();

        assertTrue(mp.removePermission(ManifestParser.USER_TAG, TEST_USER, 'w'));
        assertFalse(mp.removePermission(ManifestParser.GROUP_TAG, "nullGroup", 'r'));

        Path expected = Paths.get("testData/testRmPermSimple.mnf");
        Path actual = Paths.get(Main.DATA_DIR + TEST_FILE + ".mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testRmPermissionComplex() throws IOException, ParseException {
        Main.currentUser = new User(TEST_USER);
        ManifestParser mp = new ManifestParser(TEST_FILE);
        mp.createDefaultManifest();

        assertTrue(mp.removePermission(ManifestParser.USER_TAG, TEST_USER, 'w'));

        assertFalse(mp.removePermission(ManifestParser.USER_TAG, TEST_USER, 'w'));

        assertTrue(mp.removePermission(ManifestParser.USER_TAG, TEST_USER, 'r'));

        assertFalse(mp.removePermission(ManifestParser.USER_TAG, TEST_USER, 'w'));

        Path expected = Paths.get("testData/testRmPermComplex.mnf");
        Path actual = Paths.get(Main.DATA_DIR + TEST_FILE + ".mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testCheckPermission() throws IOException, ParseException {
        Main.currentUser = new User(TEST_USER);
        ManifestParser mp = new ManifestParser(TEST_FILE);
        mp.createDefaultManifest();

        assertTrue(mp.checkPermission(ManifestParser.USER_TAG, TEST_USER, 'r'));
        assertTrue(mp.checkPermission(ManifestParser.USER_TAG, TEST_USER, 'w'));

        assertFalse(mp.checkPermission(ManifestParser.USER_TAG, TEST_USER, 'x'));
        assertFalse(mp.checkPermission(ManifestParser.GROUP_TAG, "nullGroup", 'r'));

        Files.delete(Paths.get(Main.DATA_DIR + TEST_FILE + ".mnf"));
    }
}
