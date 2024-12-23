package main.java.movienights;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

public class GroupManager {
    private final MySQLQuerySerializer dataBase = new MySQLQuerySerializer(); // Database handler
    private final Collection<Group> groups = new ArrayList<>();
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
        if (!dataBase.hasAlreadyStoredGroup(group.getGroupID())) {
            groups.add(group);
            dataBase.save(group);
            return true;
        }
        System.out.println("Group with this ID already exists.");
        return false;
    }
    

    // Add a member to the current group
public boolean addMember(Account member, int groupID) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    for(Group group : groups){
        if(group.getGroupID()==groupID){
            this.currentGroup=group;
        }
    }
    if (currentGroup == null) {
        System.out.println("Group not selected.");
        return false;
    }
    // Check if the member is already in the group
    if (currentGroup.getMembers().contains(member)) {
        System.out.println("Member already exists in the group.");
        return false;
    }

    // Add the member and update the database
    if (currentGroup.addMember(member)) {
        dataBase.update(currentGroup);
        System.out.println("Member added successfully.");
        return true;
    } else {
        System.out.println("Group is at maximum capacity.");
        return false;
    }
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
