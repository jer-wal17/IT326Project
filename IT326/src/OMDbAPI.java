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
     */
    @Override
    public Movie search(String title)
    {
        String movieName = "";
        int movieYear = 0;

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

        // Convert the JSON string from the API to a JsonObject and JsonArray
        JsonObject searchObj = gson.fromJson(keepInputLine, JsonObject.class);
        JsonArray searchArr = (JsonArray) searchObj.get("Search");

        // Get the first search result
        JsonObject firstSearch = (JsonObject) searchArr.get(0);

        // Get the name as a String
        JsonElement movieNameJSON = firstSearch.get("Title");
        movieName = movieNameJSON.getAsString();

        // Get the year as an int
        JsonElement movieYearJSON = firstSearch.get("Year");
        movieYear = movieYearJSON.getAsInt();

        return new Movie(movieName, movieYear);
    }
}
