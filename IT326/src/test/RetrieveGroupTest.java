// author: alex Lerch
package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import main.java.movienights.*;

public class RetrieveGroupTest {

    @Test
    public void retrieveGroupThatExists() {
        QuerySerializer db = new MySQLQuerySerializer();
        Group group = new Group(1, null, null, null, null, 0);
        try {
            group = db.retrieve(group);
         } catch (Exception e) {
             fail();
         } 
        assertNotNull(group);
    }

    @Test
    public void retrieveGroupThatDoesNotExist() {
        QuerySerializer db = new MySQLQuerySerializer();
        Group group = new Group(55, null, null, null, null, 0);
        try {
            group = db.retrieve(group);
         } catch (Exception e) {
             fail();
         } 
        assertNull(db.retrieve(group));
    }
    
}
