package main.java.movienights;

public abstract class API
{
    private String URL;

    public abstract Movie search(String title);

    public abstract boolean retrieveTop3Movies(String title);
}
