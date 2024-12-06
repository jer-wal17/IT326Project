package main.java.movienights;

public class testMain {
    public static void main(String[] args) {
        
        QuerySerializer db = new MySQLQuerySerializer();
        Account acc = new Account(1);
        try {
            db.retrieve(acc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(acc.toString()); 
    }
    
}
