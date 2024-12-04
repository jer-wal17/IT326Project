//Acount Class
import java.util.ArrayList;

public class Account{
    String username;
    String phoneNumber;
    int uid;
    String password;
    ArrayList<Group> group = new ArrayList<Group>();
    public Account(){

    }
    public Account(int uid){
        this.uid=uid;
    }
    public Account(String username, String phoneNumber, int uid, String password){
        this.username=username;
        this.phoneNumber=phoneNumber;
        this.uid=uid;
        this.password=password;
    }
    public boolean setUsername(String username){
        this.username=username;
        return true;
    }
    public boolean setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
        return true;
    }
    public boolean setUID(int UID){
        this.uid=UID;
        return true;
    }
    public boolean setPassword(String password) {
        this.password=password;
        return true;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public int getUID(){
        return this.uid;
    }
    public String getPassword() {
        return this.password;
    }
}