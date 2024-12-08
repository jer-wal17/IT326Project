/**
 * Author: Zachary Kunzer
 * Class: IT326
 * Date: 12/08/2024
 * Account.java - Serves the purpose of our Account data type. Stores various information about a user.
 */
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
    public List<Group> joinedGroup = new ArrayList<>();

    public Account(){
    }
    //constructor based on UID so that we can build a partial Account object to look up if that UID exists in the database.
    public Account(int uid){
        this.uid=uid;
    }
    //full constructor for when the user creates an account
    public Account(String username, String phoneNumber, int uid, String password){
        this.username=username;
        this.phoneNumber=phoneNumber;
        this.uid=uid;
        this.password=password;
        //404 denotes they don't have a perfered decade
        this.perferedDecade=404;
    }
    
    //getters and setters
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

    //join and leave group methods.
    public void joinGroup(Group newGroup){
        this.joinedGroup.add(newGroup);
    }
    public boolean removeGroup(int groupID){
        return joinedGroup.removeIf(group-> group.getGroupID()==groupID);
    }
}