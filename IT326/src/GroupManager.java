
import java.util.Collection;


    public class GroupManager {
    
        public Collection<Group> groups;
    
        public Group retrieveGroup(int groupID) {
            return new Group();
        }
    
        public boolean createNewAccountGroup(Account account, String movie, int maxSize) {
            return true;
        }
    
        public boolean addMember(Group group, Account member) {
            return true;
        }
    
        public boolean deleteGroup(Group group) {
            return true;
        }
    
        public boolean editGroup(Group group) {
            return true;
        }
    }    
    

