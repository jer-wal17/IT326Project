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
            fail();
        }
        assertNotNull(loggedInAccount);
    }

    @Test
    public void LogInNonExistAccount(){
        try {
            loggedInAccount = manager.logIn(999, "FalsePassword");
        }
        catch (Exception e) {
            fail();
        }
        assertNull(loggedInAccount);

    }
}
