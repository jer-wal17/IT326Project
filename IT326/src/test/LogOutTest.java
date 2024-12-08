package test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import main.java.movienights.*;


public class LogOutTest {
    private Controller controller;

@Test
    public void testLogOutSuccess() {
        try {
            // Simulate login first
            boolean loginSuccess = controller.logIn(1, "password");
            assertTrue("Login should succeed", loginSuccess);

            // Now log out
            boolean logoutSuccess = controller.logOut();
            assertTrue("Log out should succeed", logoutSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
@Test
    public void testLogOutWithoutLogin() {
        try {
            // Attempt to log out without logging in
            boolean logoutSuccess = controller.logOut();
            assertFalse("Log out should fail without a logged-in account", logoutSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        };
        }
    }
    

