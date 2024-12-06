// author: Alex Lerch

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class RetrieveAccountTest {

    @Test
    void retrieveAccountThatExists() {
        QuerySerializer db = new MySQLQuerySerializer();
        Account acc = new Account(1);
        assertNotNull(db.retrieve(acc));
    }

    @Test
    void retrieveAccountThatDoesNotExist() {
        QuerySerializer db = new MySQLQuerySerializer();
        Account acc = new Account(55);
        assertNull(db.retrieve(acc));
    }
    
    
}

