package test;

import org.junit.Test;
import main.java.movienights.*;

import static org.junit.Assert.*;

public class APITop3Test {
    OMDbAPI myAPI = (OMDbAPI) APIFactory.getAPI(APIFactory.APIType.OMDB);

    @Test
    public void ValidMovieTitleTest() {
        assertTrue(myAPI.retrieveTop3Movies("tron legacy"));
    }

    @Test
    public void InvalidMovieTitleTest() {
        assertFalse(myAPI.retrieveTop3Movies("adsagewrg"));
    }
}
