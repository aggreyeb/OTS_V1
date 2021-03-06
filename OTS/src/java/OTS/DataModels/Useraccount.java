package OTS.DataModels;
// Generated 6-Feb-2016 4:41:34 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Useraccount generated by hbm2java
 */
public class Useraccount  implements java.io.Serializable {


     private Integer userAccountId;
     private String userName;
     private String password;
     private Boolean isLocked;
     private Set users = new HashSet(0);

    public Useraccount() {
    }

	
    public Useraccount(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
    public Useraccount(String userName, String password, Boolean isLocked, Set users) {
       this.userName = userName;
       this.password = password;
       this.isLocked = isLocked;
       this.users = users;
    }
   
    public Integer getUserAccountId() {
        return this.userAccountId;
    }
    
    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getIsLocked() {
        return this.isLocked;
    }
    
    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }




}


