package main.java.movienights;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class GroupManager {
    private final QuerySerializer dataBase = new MySQLQuerySerializer(); // Database handler
    private Collection<Group> groups = new ArrayList<>();
    private Group currentGroup;

    public Group retrieveGroup(int groupID) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{
        if (dataBase.hasAlreadyStored(groupID)) {
            Account account = new Account("defaultUsername", "defaultPhoneNumber", 0, "defaultPassword"); 
            return dataBase.retrieve(new Group(groupID, null, "defaultAddress", LocalDate.now(), account, 1));
        }
        return null; // Return null if the group is not stored
    }

    // Create a new group
    public boolean createGroup(Group group) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (!dataBase.hasAlreadyStored(group.getGroupID())) {
            groups.add(group);
            dataBase.save(group);
            return true;
        }
        System.out.println("Group with this ID already exists.");
        return false;
    }
    

    // Add a member to the current group
    public boolean addMember(Account member) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (currentGroup != null && !currentGroup.getMembers().contains(member)) {
            if (currentGroup.addMember(member)) {
                dataBase.update(currentGroup);
                System.out.println("Member added successfully.");
                return true;
            } else {
                System.out.println("Group is at maximum capacity.");
            }
        } else {
            System.out.println("Group not selected or member already exists.");
        }
        return false;
    }

    // List all groups
    public Collection<Group> listGroup() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        groups = dataBase.retrieve(); 
        return groups;
    }

    // Delete a group by its ID
    public boolean deleteGroup(int groupID) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Group groupToDelete = retrieveGroup(groupID);
        if (groupToDelete != null) {
            dataBase.delete(groupToDelete);
            groups.remove(groupToDelete);
            System.out.println("Group deleted successfully.");
            return true;
        }
        System.out.println("Group not found.");
        return false;
    }

    // Edit an existing group's details
    public boolean editGroup(Group group) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (group != null && dataBase.hasAlreadyStored(group.getGroupID())) {
            dataBase.update(group);
            System.out.println("Group updated successfully.");
            return true;
        }
        System.out.println("Group not found or update failed.");
        return false;
    }

    // Change the owner of the current group
    public boolean changeOwner(Account member) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (currentGroup != null && currentGroup.getMembers().contains(member)) {
            currentGroup.setOwner(member);
            dataBase.update(currentGroup);
            System.out.println("Group owner changed successfully.");
            return true;
        }
        System.out.println("Group not selected or member not part of the group.");
        return false;
    }

    // Update an existing group's details
    public boolean updateGroup(Group group) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        if (group != null && dataBase.hasAlreadyStored(group.getGroupID())) {
            dataBase.update(group);
            System.out.println("Group updated successfully.");
            return true;
        }
        System.out.println("Group not found or update failed.");
        return false;
    }
}
