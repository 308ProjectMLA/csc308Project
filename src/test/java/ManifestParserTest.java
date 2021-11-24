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

    @Test
    public void testCreateDefault() throws IOException {
        Main.currentUser = new User("testyAdmin");
        ManifestParser mp = new ManifestParser("testMP");
        mp.createDefaultManifest();

        Path expected = Paths.get("testData/testCreateMan.mnf");
        Path actual = Paths.get("data/testMP.mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testAddPermissionSimple() throws IOException, ParseException {
        Main.currentUser = new User("testyAdmin");
        ManifestParser mp = new ManifestParser("testMP");
        mp.createDefaultManifest();

        assertTrue(mp.addPermission(ManifestParser.USER_TAG, "larry", 'r'));
        assertTrue(mp.addPermission(ManifestParser.GROUP_TAG, "writers", 'w'));

        Path expected = Paths.get("testData/testAddPermSimple.mnf");
        Path actual = Paths.get("data/testMP.mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testAddPermissionComplex() throws IOException, ParseException {
        Main.currentUser = new User("testyAdmin");
        ManifestParser mp = new ManifestParser("testMP");
        mp.createDefaultManifest();

        assertTrue(mp.addPermission(ManifestParser.USER_TAG, "larry", 'w'));
        assertTrue(mp.addPermission(ManifestParser.USER_TAG, "larry", 'r'));
        assertTrue(mp.addPermission(ManifestParser.GROUP_TAG, "writers", 'r'));
        assertTrue(mp.addPermission(ManifestParser.GROUP_TAG, "writers", 'w'));

        assertFalse(mp.addPermission(ManifestParser.GROUP_TAG, "writers", 'w'));
        assertFalse(mp.addPermission(ManifestParser.USER_TAG, "testyAdmin", 'r'));


        Path expected = Paths.get("testData/testAddPermComplex.mnf");
        Path actual = Paths.get("data/testMP.mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testRmPermissionSimple() throws IOException, ParseException {
        Main.currentUser = new User("testyAdmin");
        ManifestParser mp = new ManifestParser("testMP");
        mp.createDefaultManifest();

        assertTrue(mp.removePermission(ManifestParser.USER_TAG, "testyAdmin", 'w'));
        assertFalse(mp.removePermission(ManifestParser.GROUP_TAG, "nullGroup", 'r'));

        Path expected = Paths.get("testData/testRmPermSimple.mnf");
        Path actual = Paths.get("data/testMP.mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testRmPermissionComplex() throws IOException, ParseException {
        Main.currentUser = new User("testyAdmin");
        ManifestParser mp = new ManifestParser("testMP");
        mp.createDefaultManifest();

        assertTrue(mp.removePermission(ManifestParser.USER_TAG, "testyAdmin", 'w'));

        assertFalse(mp.removePermission(ManifestParser.USER_TAG, "testyAdmin", 'w'));

        assertTrue(mp.removePermission(ManifestParser.USER_TAG, "testyAdmin", 'r'));

        assertFalse(mp.removePermission(ManifestParser.USER_TAG, "testyAdmin", 'w'));

        Path expected = Paths.get("testData/testRmPermComplex.mnf");
        Path actual = Paths.get("data/testMP.mnf");

        assertEquals(-1, Files.mismatch(expected, actual));
        Files.delete(actual);
    }

    @Test
    public void testCheckPermission() throws IOException, ParseException {
        Main.currentUser = new User("testyAdmin");
        ManifestParser mp = new ManifestParser("testMP");
        mp.createDefaultManifest();

        assertTrue(mp.checkPermission(ManifestParser.USER_TAG, "testyAdmin", 'r'));
        assertTrue(mp.checkPermission(ManifestParser.USER_TAG, "testyAdmin", 'w'));

        assertFalse(mp.checkPermission(ManifestParser.USER_TAG, "testyAdmin", 'x'));
        assertFalse(mp.checkPermission(ManifestParser.GROUP_TAG, "nullGroup", 'r'));

        Files.delete(Paths.get("data/testMP.mnf"));
    }
}
