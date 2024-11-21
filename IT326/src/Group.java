import java.time.LocalDate;
import java.util.List;

public class Group {
  int maxSize;
  int groupID;
  String movie;
  String meetingAddress;
  List<Account> members;
  LocalDate meetingDate;
  
  public Group(int groupID, String movie, List<Account> members, String meetingAddress, LocalDate meetingDate) {
    this.groupID = groupID;
    this.movie = movie;
    this.members = members;
    this.meetingAddress = meetingAddress;
    this.meetingDate = meetingDate;
}
public int getGroupID() {
    return groupID;
}

public void setGroupID(int groupID) {
    this.groupID = groupID;
}

public String getMovie() {
    return movie;
}

public void setMovie(String movie) {
    this.movie = movie;
}

public List<Account> getMembers() {
    return members;
}

public void setMembers(List<Account> members) {
    this.members = members;
}

public String getMeetingAddress() {
    return meetingAddress;
}

public void setMeetingAddress(String meetingAddress) {
    this.meetingAddress = meetingAddress;
}

public LocalDate getMeetingDate() {
    return meetingDate;
}

public void setMeetingDate(LocalDate meetingDate) {
    this.meetingDate = meetingDate;
}
}


  

