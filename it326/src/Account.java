//Acount Class
public class Account{
    String username;
    int phoneNumber;
    int uid;
    public Account(String username, int phoneNumber, int uid){
        this.username=username;
        this.phoneNumber=phoneNumber;
        this.uid=uid;
    }
    public boolean setUsername(String username){
        this.username=username;
        return true;
    }
    public boolean setPhoneNumber(int phoneNumber){
        this.phoneNumber=phoneNumber;
        return true;
    }
    public boolean setUID(int UID){
        this.uid=UID;
        return true;
    }
    public String getUsername(){
        return this.username;
    }
    public int getPhoneNumber(){
        return this.phoneNumber;
    }
    public int getUID(){
        return this.uid;
    }
}