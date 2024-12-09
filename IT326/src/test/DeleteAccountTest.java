/**
 * Author: Zachary Kunzer
 * Class: IT326
 * Date: 12/08/2024
 * DeleteAccountTest.java - Junit testing for delete account;
 */

package test;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;


import main.java.movienights.*;

public class DeleteAccountTest {
    boolean deleteResult;

    @Test
    public void accountExists(){
        try {
            AccountManager manager = new AccountManager();
            manager.createAccount("TestAccount", "TestPassword", "(999)999-9999", 404);
            deleteResult = manager.deleteAccount();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(deleteResult);
    }

    @Test
    public void accountDoesNotExist(){
        try {
            AccountManager manager = new AccountManager();
            deleteResult = manager.deleteAccount();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertFalse(deleteResult);
    }
}
