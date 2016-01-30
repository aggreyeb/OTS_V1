/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.*;
import OTS.DataModels.DataSource;
import OTS.DataModels.Departmenttype;
import OTS.DataModels.User;
import OTS.DataModels.Useraccount;
import OTS.DataModels.Usertype;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MEA
 */
public class PublicUniversity extends University {
   
    DataSource dataSource;
  
    public PublicUniversity(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public void CreateDepartment(String name,Callback callback) {
   
       
    }

    @Override
    public void CreateAccount(Credential credential, PersonName name, int roleTypeId,Callback callback) {
        int id=0;
        
        try{
       this.dataSource.Open();
       this.dataSource.BeginTransaction();  
       Usertype ut= (Usertype)this.dataSource.Find(Usertype.class,new Integer(roleTypeId));
        Useraccount ua= new Useraccount();
        ua.setUserName(credential.UserName);
        ua.setPassword(credential.Password);
        ua.setIsLocked(Boolean.FALSE);
        this.dataSource.Save(ua);
       User user = new User();
     //  user.setIdentity(Identity.NewGiudIdentity().ID);
       user.setFirstName(name.FirstName);
       user.setLastName(name.LastName);
       user.setUsertype(ut);
       
      
       this.dataSource.Save(user);
       this.dataSource.Commit();
       id=ua.getUserAccountId();
        callback.OnSucces(id, "ok");
       }
       catch(Throwable ex){
           
          this.dataSource.Rollback();
          callback.OnFailure(-1,"fail",ex.toString());
       }
       finally{
         this.dataSource.Close();
       }
    }

    @Override
    public void CreateBatchAccounts( RoleCredential[] credentials,Callback callback) {
       
        try{
         this.dataSource.Open();
         this.dataSource.BeginTransaction();  
         for(int i=0;i<credentials.length;i++){
          Usertype ut= (Usertype)this.dataSource.Find(Usertype.class,new Integer(credentials[i].RoleTypeId));
         Useraccount ua= new Useraccount();
         ua.setUserName(credentials[i].UserName);
         ua.setPassword(credentials[i].Password);
         ua.setIsLocked(Boolean.FALSE);
        this.dataSource.Save(ua);
        User user = new User();
        user.setFirstName(credentials[i].FirstName);
        user.setLastName(credentials[i].LastName);
        user.setUseraccount(ua);
        user.setUsertype(ut);
        this.dataSource.Save(user);
      }
      this.dataSource.Commit();   
         callback.OnSucces(-1, "ok");   
     }
     catch(Throwable ex){
        
        this.dataSource.Rollback();
         callback.OnFailure(-1,"fail",ex.toString());
     }
     finally{
           this.dataSource.Close();
        } 
     
    }
    
    @Override
    public void ResetPassward(Credential credential, String newPassword,Callback callback) {
      
         String sql=String.format("update useraccount set EncreptedPassword='%1$s'"
                + " where UserName='%2$s' and Encreptedpassword='%3$s'",
               newPassword, credential.UserName,credential.Password);
        
        try{
            this.dataSource.Open();
            this.dataSource.BeginTransaction();
            int[] result= new int[1];
            this.dataSource.ExecuteNonQuery(sql);
            this.dataSource.Commit();
            this.Authenticate(new Credential(credential.UserName,newPassword), callback);
            
            callback.OnSucces(-1, "ok");
        }
        catch(Throwable ex){
              callback.OnSucces(-1, "fail");
        }
        finally{
           this.dataSource.Close();
        }
    }


    @Override
    public void Authenticate(Credential credential,Callback callback) {
    
        UserProfile profile=this.LoadUserProfile(credential.UserName, credential.Password);
         if(profile !=null){
        
             credential.OnAthenticated(profile, "ok");
              callback.OnSucces(-1,"ok");
         }
         else{
             credential.OnFailAthenticated("fail");
             callback.OnFailure(-1,"fail","Invalid UserName or Password");
         }
    }

    
    @Override
    public void AssignRoleDepartment(Identity identity, int departmentId,Callback callback) {
      
    }

    @Override
    public void UnAssignRoleDepartment(Identity identity, int departmentId,Callback callback) {
       
    }

    
     public UserProfile LoadUserProfile(String userName, String password){
      
          UserProfile userProfile=null;
         try
         {
        String sql ="select u.UserId , \n" +
                        "u.FirstName,\n" +
                        "u.LastName,\n" +
                        "u.UserAccountId,\n" +
                        "t.UserTypeId,\n" +
                        "t.Name as UserTypeName,\n" +
                        "t.HomePageName\n" +
                        "from user u \n" +
                        "inner join useraccount a\n" +
                        "on u.UserAccountId=a.UserAccountId\n" +
                        "inner join usertype t\n" +
                        "on u.UserTypeId=t.UserTypeId\n" +
                        "where BINARY a.UserName=" + "'" + userName + "'" +  "and BINARY a.Password= " + "'" + password + "'";
           // this.dataSource.BeginTransaction();
            List<UserProfile> list= new ArrayList();
            this.dataSource.Open();
            this.dataSource.ExecuteCustomDataSet(sql, list,UserProfile.class);
            if(list.size()>0){
               userProfile=list.get(0);
            }
             
            return userProfile;
         }
         catch(Throwable ex){
         
             return userProfile;
         }
         finally{
             this.dataSource.Close();
         }
         
     }
  
    
}
