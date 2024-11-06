import com.google.gson.*;

public class App
{
    public static void main(String[] args) throws Exception
    {

        System.out.println("Hello, World!");
 
        // connect to database
        MySQLConnectionSerializer connector = new MySQLConnectionSerializer();
        connector.connect();

        // query the database and print results
        QueryTester qt = new QueryTester();
        String accountToPrint = "alexlerch";
        qt.retrieveAccount(accountToPrint, connector.getConn());

        // close the database
        connector.disconnect();

    }
}