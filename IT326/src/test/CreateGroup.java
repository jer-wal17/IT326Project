package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.java.movienights.*;

public class CreateGroup {

    private Controller controller;
    @Test
    public void testCreateGroupSuccess() {
        try {
            // Simulate a valid group creation
            Group group = new Group(0, null, "Movie Enthusiasts", null, null, 1); // Assuming 1 is the creator's UID
            boolean createSuccess = controller.createGroup(group);

            assertTrue("Group creation should succeed", createSuccess);
        } catch (Exception e) {
            fail("Exception occurred during createGroup test: " + e.getMessage());
        }
    }

    @Test
    public void testCreateGroupFailure() {
        try {
            // Simulate an invalid group creation, e.g., null group
            boolean createSuccess = controller.createGroup(null);

            assertFalse("Group creation should fail when group is null", createSuccess);
        } catch (Exception e) {
            fail("Exception occurred during createGroup test: " + e.getMessage());
        }
    }
    
}
