package main.java.movienights;

//Acount Class
import java.util.ArrayList;
import java.util.List;

public class Account{
    String username;
    String phoneNumber;
    int uid;
    String password;
    int perferedDecade;
    List<Group> group = new ArrayList<>();
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
    public Account(String username, String phoneNumber, int uid, String password, int perDec){
        this.username=username;
        this.phoneNumber=phoneNumber;
        this.uid=uid;
        this.password=password;
        this.perferedDecade=perDec;
    }
    public Account(String username, String phoneNumber, int uid, String password, int perDec, ArrayList<Group> joinedGroups){
        this.username=username;
        this.phoneNumber=phoneNumber;
        this.uid=uid;
        this.password=password;
        this.perferedDecade=perDec;
        this.group = joinedGroups;
    }
    public Account(String username, String phoneNumber, int uid, String password, ArrayList<Group> joinedGroups){
        this.username=username;
        this.phoneNumber=phoneNumber;
        this.uid=uid;
        this.password=password;
        this.perferedDecade=404;
        this.group = joinedGroups;
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
    public boolean setPereredDecade(int decade){
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