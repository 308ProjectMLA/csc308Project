import com.example.csc308project.GroupController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroupControllerTest {

    @Test
    public void testIsSupervisor() {
        assertTrue(GroupController.isSupervisor("admin"));
    }

    @Test
    public void testIsntSupervisor() {
        assertFalse(GroupController.isSupervisor("larry"));
    }
}
