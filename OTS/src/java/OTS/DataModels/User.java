package OTS.DataModels;
// Generated Feb 3, 2016 3:28:45 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer userId;
     private Useraccount useraccount;
     private Usertype usertype;
     private String email;
     private String phone;
     private String firstName;
     private String lastName;
     private String number;
     private String street;
     private String city;
     private String province;
     private Set studenttests = new HashSet(0);
     private Set studentcourseregistrations = new HashSet(0);
     private Set teachercoursetests = new HashSet(0);
     private Set knowledgemaps = new HashSet(0);
     private Set studenttestanswersheets = new HashSet(0);

    public User() {
    }

	
    public User(Useraccount useraccount, String firstName, String lastName) {
        this.useraccount = useraccount;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(Useraccount useraccount, Usertype usertype, String email, String phone, String firstName, String lastName, String number, String street, String city, String province, Set studenttests, Set studentcourseregistrations, Set teachercoursetests, Set knowledgemaps, Set studenttestanswersheets) {
       this.useraccount = useraccount;
       this.usertype = usertype;
       this.email = email;
       this.phone = phone;
       this.firstName = firstName;
       this.lastName = lastName;
       this.number = number;
       this.street = street;
       this.city = city;
       this.province = province;
       this.studenttests = studenttests;
       this.studentcourseregistrations = studentcourseregistrations;
       this.teachercoursetests = teachercoursetests;
       this.knowledgemaps = knowledgemaps;
       this.studenttestanswersheets = studenttestanswersheets;
    }
   
    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Useraccount getUseraccount() {
        return this.useraccount;
    }
    
    public void setUseraccount(Useraccount useraccount) {
        this.useraccount = useraccount;
    }
    public Usertype getUsertype() {
        return this.usertype;
    }
    
    public void setUsertype(Usertype usertype) {
        this.usertype = usertype;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    public String getStreet() {
        return this.street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    public Set getStudenttests() {
        return this.studenttests;
    }
    
    public void setStudenttests(Set studenttests) {
        this.studenttests = studenttests;
    }
    public Set getStudentcourseregistrations() {
        return this.studentcourseregistrations;
    }
    
    public void setStudentcourseregistrations(Set studentcourseregistrations) {
        this.studentcourseregistrations = studentcourseregistrations;
    }
    public Set getTeachercoursetests() {
        return this.teachercoursetests;
    }
    
    public void setTeachercoursetests(Set teachercoursetests) {
        this.teachercoursetests = teachercoursetests;
    }
    public Set getKnowledgemaps() {
        return this.knowledgemaps;
    }
    
    public void setKnowledgemaps(Set knowledgemaps) {
        this.knowledgemaps = knowledgemaps;
    }
    public Set getStudenttestanswersheets() {
        return this.studenttestanswersheets;
    }
    
    public void setStudenttestanswersheets(Set studenttestanswersheets) {
        this.studenttestanswersheets = studenttestanswersheets;
    }




}


