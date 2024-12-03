
import java.util.ArrayList;
import java.util.List;

public class GroupManager {
    private List<Group> groups; // Collection of groups

    public GroupManager() {
        this.groups = new ArrayList<>();
    }

    public Group retrieveGroup(int groupID) {
        for (Group group : groups) {
            if (group.getGroupID() == groupID) {
                return group;
            }
        }
        return null; // Group not found
    }

    public boolean createGroup(Group group) {
        if (retrieveGroup(group.getGroupID()) != null) {
            return false; // Group with this ID already exists
        }
        groups.add(group);
        return true;
    }

    public boolean changeOwner(int groupID, Account newOwner) {
        Group group = retrieveGroup(groupID);
        if (group != null && group.getMembers().contains(newOwner)) {
            group.setOwner(newOwner);
            return true;
        }
        return false;
    }

    public boolean addMember(int groupID, Account member) {
        Group group = retrieveGroup(groupID);
        if (group != null && group.getMembers().size() < group.getMaxSize() && !group.getMembers().contains(member)) {
            group.getMembers().add(member);
            return true;
        }
        return false;
    }

    public List<Group> listGroups() {
        return groups;
    }

    public boolean deleteGroup(int groupID) {
        Group group = retrieveGroup(groupID);
        if (group != null) {
            groups.remove(group);
            return true;
        }
        return false;
    }
        }



