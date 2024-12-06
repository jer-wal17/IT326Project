// author: alex Lerch

import org.junit.jupiter.api.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

public class RetrieveGroupTest {

    @Test
    void retrieveGroupThatExists() {
        QuerySerializer db = new MySQLQuerySerializer();
        Group group = new Group(1, null, null, null, null, 0);
        assertNotNull(db.retrieve(group));
    }

    @Test
    void retrieveGroupThatDoesNotExist() {
        QuerySerializer db = new MySQLQuerySerializer();
        Group group = new Group(55, null, null, null, null, 0);
        assertNull(db.retrieve(group));
    }
    
}
