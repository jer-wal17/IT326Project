import java.time.LocalDate;

public class App
{
    public static void main(String[] args) throws Exception
    {
        
        QuerySerializer db = new MySQLQuerySerializer();

        // check for retrieve account
        System.out.println("checking for retrieve account\n");
        Account myAccount = new Account(1);
        myAccount = db.retrieve(myAccount);
        System.out.println("myAccount:\nusername: " + myAccount.getUsername() + "\nphone number: " + myAccount.getPhoneNumber() + "\nuid: " + myAccount.getUID() + "\npassword: " + myAccount.getPassword() + "\ngroups: ");
        for (Group curGroup : myAccount.group) {
            System.out.println("\n\t" + curGroup.getMovie().getMovieName() + "\n\tmeeting address : " + curGroup.getMeetingAddress() + "\n\tmeeting date: " + curGroup.getMeetingDate().toString());
        }

        


        // check for save group
        LocalDate newDate = LocalDate.of(2025, 01, 12);
        Movie newMovie = new Movie("redemption");
        System.out.println("newMovie title: " + newMovie.getMovieName());
        Group newGroup = new Group (12, newMovie, "34 third st", newDate, myAccount, 0);
        int gid = db.save(newGroup);


        // check for retrieve group
        System.out.println("checking for retrieve group with id = " + gid + "\n");
        Group myGroup = new Group(gid, new Movie("pie"), "", null, new Account(), 1);
        myGroup = db.retrieve(myGroup);
        System.out.println("\n\t" + myGroup.getMovie().getMovieName() + "\n\tmeeting address : " + myGroup.getMeetingAddress() + "\n\tmeeting date: " + myGroup.getMeetingDate().toString());
        for (Account curAccount : myGroup.getMembers()) {
            System.out.println("\n\t" + curAccount.getUsername() + "\n\t" + "uid: " + curAccount.getUID() + "\n\tpassword: " + curAccount.getPassword() + "\n\tphone number: " + curAccount.getPhoneNumber() + "\n" );
        }

        // check for delete group


    }
}