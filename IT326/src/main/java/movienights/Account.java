package main.java.movienights;

//Acount Class
import java.util.ArrayList;
import java.util.List;

public class Account{
    private String username;
    private String phoneNumber;
    private int uid;
    private String password;
    private int perferedDecade;
    public List<Group> group = new ArrayList<>();
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
        //404 denotes they don't have a perfered decade
        this.perferedDecade=404;
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
    public boolean setPerferedDecade(int decade){
        this.perferedDecade=decade;
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
    public int getDecade(){
        return this.perferedDecade;
    }
}