package main.java.movienights;

public class Movie {
    private String movieName;
    private int movieYear;

    protected Movie(String name, int year) {
        this.movieName = name;
        this.movieYear = year;
    }

    public Movie(String title) {
        OMDbAPI myAPI = (OMDbAPI) APIFactory.getAPI(APIFactory.APIType.OMDB);
        Movie myMovie = myAPI.search(title);
        
        this.movieName = myMovie.getMovieName();
        this.movieYear = myMovie.getMovieYear();
    }

    public String getMovieName(){
        return this.movieName;
    }

    public int getMovieYear(){
        return this.movieYear;
    }

    @Override
    public String toString()
    {
        String outString = "";
        outString += "Title: " + movieName + "\n";
        outString += "Release Year: " + movieYear + "\n";

        return outString;
    }
}