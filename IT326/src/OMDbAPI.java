import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

import com.google.gson.*;

public class OMDbAPI extends API
{
    private String URL = "http://www.omdbapi.com/?apikey=fad4d002&type=movie&s=";

    /**
     * Method that returns the first search result from the API as a Movie object
     * based on the given title
     * 
     * @param title The title of the movie to search
     * @return The Movie found based on the first search result
     */
    @Override
    public Movie search(String title)
    {
        String movieName = "";
        int movieYear = 0;

        JsonObject searchObj = retrieveSearchObject(title);
        if (searchObj == null)
        {
            throw new IllegalArgumentException();
        }

        // Convert the searchObj to a JsonArray
        JsonArray searchArr = (JsonArray) searchObj.get("Search");

        // Get the first search result
        JsonObject firstSearch = (JsonObject) searchArr.get(0);

        // Get the name as a String
        movieName = firstSearch.get("Title").getAsString();

        // Get the year as an int
        movieYear = firstSearch.get("Year").getAsInt();

        return new Movie(movieName, movieYear);
    }

    /**
     * Method that retrieves and prints out the top 3 search results based on the
     * given title
     * 
     * @param title
     */
    @Override
    public boolean retrieveTop3Movies(String title)
    {
        JsonObject searchObj = retrieveSearchObject(title);
        if (searchObj == null)
        {
            return false;
        }

        // Convert the searchObj to a JsonArray
        JsonArray searchArr = (JsonArray) searchObj.get("Search");

        // Get the total results and check that it's at least 3
        int totalResults = searchObj.get("totalResults").getAsInt();
        int numLoops = 0;

        if (totalResults < 3)
        {
            numLoops = totalResults;
        }
        else
        {
            numLoops = 3;
        }

        // Print top 3 results
        for (int i = 0; i < numLoops; i++)
        {
            // Get the search result
            JsonObject searchResult = (JsonObject) searchArr.get(i);

            // Get the name as a String
            JsonElement movieNameJSON = searchResult.get("Title");
            String movieName = movieNameJSON.getAsString();
            System.out.println("Movie #" + (i + 1) + ": " + movieName);
        }
        System.out.println();

        return true;
    }

    /**
     * Private helper method that performs the API call and the initial JSON
     * serialization
     * 
     * @param title The title to search for
     * @return The search results as a JsonObject
     */
    private JsonObject retrieveSearchObject(String title)
    {
        // Get given title ready for API search and add to API URL
        title = title.toLowerCase();
        title = title.replaceAll(" ", "+");
        String searchURL = URL + title;

        // Set up variables for later use
        URL myURL = null;
        HttpURLConnection myConn = null;
        String keepInputLine = "";

        try
        {
            // Set up URL and connect to it to get API response
            myURL = new URL(searchURL);
            myConn = (HttpURLConnection) myURL.openConnection();
            myConn.setRequestMethod("GET");
            int responseCode = myConn.getResponseCode();
            // If we get a good response, put it into keepInputLine
            if (responseCode == HttpURLConnection.HTTP_OK)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(myConn.getInputStream()));
                String inputLine = "";

                while ((inputLine = in.readLine()) != null)
                {
                    keepInputLine += inputLine;
                }
                in.close();
                myConn.disconnect();
            }
            else
            {
                System.out.println("GET request did not work.");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Create a new GSON object for use with the JSON from the API
        Gson gson = new Gson();

        // Convert the JSON string from the API to a JsonObject
        JsonObject searchObj = gson.fromJson(keepInputLine, JsonObject.class);

        if (searchObj.get("Response").getAsString().equals("False"))
        {
            System.out.println(searchObj.get("Error").getAsString());
        }
        else
        {
            return searchObj;
        }

        return null;
    }
}
