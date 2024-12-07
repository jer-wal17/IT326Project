package test;

import org.junit.Test;
import main.java.movienights.*;
import static org.junit.Assert.*;

public class APISearchTest {
    @Test
    public void ValidMovieTitleTest() {
        Movie myMovie = new Movie("tron legacy");
        assertEquals("Tron: Legacy", myMovie.getMovieName());
        assertEquals(2010, myMovie.getMovieYear());
    }

    @Test(expected = IllegalArgumentException.class)
    public void InvalidMovieTitleTest() {
        Movie myMovie = new Movie("awhrsweawe");
    }
}
