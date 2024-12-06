package main.java.movienights;

public class App {
    public static void main(String[] args) {
        OMDbAPI myAPI = (OMDbAPI) APIFactory.getAPI(APIFactory.APIType.OMDB);
        System.out.println(new Movie("tron legacy"));
        myAPI.retrieveTop3Movies("tron legacy");
    }
}
