public class App
{
    public static void main(String[] args) throws Exception
    {
        OMDbAPI myAPI = (OMDbAPI) APIFactory.getAPI(APIFactory.APIType.OMDB);
        System.out.println(new Movie("Tron Legacy"));
        myAPI.retrieveTop3Movies("Tron Legacy");
    }
}
