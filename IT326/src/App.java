public class App
{
    public static void main(String[] args) throws Exception
    {
        
        QuerySerializer db = new MySQLQuerySerializer();
        Account myAccount = new Account(1);
        myAccount = db.retrieve(myAccount);
        System.out.println("myAccount:\nusername: " + myAccount.getUsername() + "\nphone number: " + myAccount.getPhoneNumber() + "\nuid: " + myAccount.getUID() + "\npassword: " + myAccount.getPassword() + "\ngroups: ");
        for (Group curGroup : myAccount.group) {
            System.out.println("\n\t" + curGroup.getMovie().getMovieName() + "\n\tmeeting address : " + curGroup.getMeetingAddress() + "\n\tmeeting date: " + curGroup.getMeetingDate().toString());
        }

    }
}