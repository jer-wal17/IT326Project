public abstract class API
{
    private String URL;

    public abstract Movie search(String title);

    public abstract void retrieveTop3Movies(String title);
}
