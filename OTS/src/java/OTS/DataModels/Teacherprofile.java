package OTS.DataModels;
// Generated Dec 20, 2014 11:09:02 AM by Hibernate Tools 4.3.1



/**
 * Teacherprofile generated by hbm2java
 */
public class Teacherprofile  implements java.io.Serializable {


     private Integer teacherProfileId;
     private User user;
     private String title;
     private String rank;

    public Teacherprofile() {
    }

    public Teacherprofile(User user, String title, String rank) {
       this.user = user;
       this.title = title;
       this.rank = rank;
    }
   
    public Integer getTeacherProfileId() {
        return this.teacherProfileId;
    }
    
    public void setTeacherProfileId(Integer teacherProfileId) {
        this.teacherProfileId = teacherProfileId;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getRank() {
        return this.rank;
    }
    
    public void setRank(String rank) {
        this.rank = rank;
    }




}


