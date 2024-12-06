// author: Alex Lerch
package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.java.movienights.*;

public class RetrieveAccountTest {

    @Test
    public void retrieveAccountThatExists() {
        QuerySerializer db = new MySQLQuerySerializer();
        Account acc = new Account(1);
        try {
           acc = db.retrieve(acc);
        } catch (Exception e) {
            fail();
        } 
        assertNotNull(acc);
    }
    

    @Test
    public void retrieveAccountThatDoesNotExist() {
        QuerySerializer db = new MySQLQuerySerializer();
        Account acc = new Account(55);
        try {
            acc = db.retrieve(acc);
         } catch (Exception e) {
             fail();
         }
        assertNull(acc);
    }
    
    
}

