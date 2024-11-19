import java.util.Collection;

public class Group {
  int maxSize;
  int groupID;
  String movie;
  Collection <Account> members;

  public int getMaxSize() {
    return maxSize;
}
public void setMaxSize(int maxSize) {
    this.maxSize = maxSize;
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
public Collection<Account> getMembers() {
    return members;
}
public void setMembers(Collection<Account> members) {
    this.members = members;
}
  
}
