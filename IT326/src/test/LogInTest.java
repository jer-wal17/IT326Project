/**
 * Author: Zachary Kunzer
 * Class: IT326
 * Date: 12/08/2024
 * LogInTest.java - Junit testing for logging into the system; 
 */
package test;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;


import main.java.movienights.*;

public class LogInTest {
    private final AccountManager manager = new AccountManager();
    Account loggedInAccount;

    @Test
    public void LogInExistAccount(){
        try {
            manager.createAccount("TestCreation", "TestPassword", "(999)999-9999", 404);
            loggedInAccount = manager.logIn(404, "TestPassword");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(loggedInAccount);
    }

    @Test
    public void LogInNonExistAccount(){
        try {
            loggedInAccount = manager.logIn(999, "FalsePassword");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertNull(loggedInAccount);

    }
}
