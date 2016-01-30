/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.DataModels.DataSource;
import OTS.DataModels.Useraccount;
import OTS.DataModels.Usertype;
import com.google.gson.Gson;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author MEA
 */
public class Users {
    
    public Response response;
    DataSource dataSource;
    char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    
    
    public Users(Response response, DataSource db) {
        this.response = response;
        this.dataSource = db;
    }
   
    public static String randomString(char[] characterSet, int length) {
    Random random = new SecureRandom();
    char[] result = new char[length];
    for (int i = 0; i < result.length; i++) {
        // picks a random index out of character set > random character
        int randomCharIndex = random.nextInt(characterSet.length);
        result[i] = characterSet[randomCharIndex];
    }
    return new String(result);
}
    
    
    public void ListUser(int userType){
         try{
       // String sql= "select UserId as Id,FirstName,LastName,Phone,Email,"
              //  +   "UserTypeId from user where UserTypeId=" + userType;
        String sql="select u.UserId as Id,\n" +
                    "       u.FirstName,\n" +
                    "		 u.LastName,\n" +
                    "		 u.Phone,\n" +
                    "		 u.Email,\n" +
                    "		 a.Password\n" +
                    " from user u inner join useraccount a on u.UserAccountId=a.UserAccountId\n" +
                    " where u.UserTypeId=" + userType;
       
        List<OTS.DataModels.User> users= new ArrayList();
        this.dataSource.Open();
        this.dataSource.ExecuteCustomDataSet(sql, users,UserAccountItem.class);
        Gson g = new Gson();
        this.response.ChangeContent(g.toJson(users));
        this.response.ChangeStatus("ok");
      }
      catch(Throwable ex){
        response.ChangeContent("");
        response.ChangeStatus("exception");
        response.UpdateError(ex.toString());
      }
      finally{
        this.dataSource.Close();
      }
    }
    
     public void ResetPassword(int userId){
        
        try{
           this.dataSource.Open();
           this.dataSource.BeginTransaction();
           OTS.DataModels.User user= (OTS.DataModels.User)dataSource.Find(OTS.DataModels.User.class, new Integer(userId));
           String password=randomString(CHARSET_AZ_09,5);
          Useraccount ua=  user.getUseraccount();
           ua.setPassword(password);
           this.dataSource.Update(ua);
           this.dataSource.Commit();
           response.UpdateID(userId);
           response.ChangeContent("");
           response.ChangeStatus("ok");
        }
        catch(Throwable ex){
            this.dataSource.Rollback();
            response.UpdateID(0);
            response.ChangeContent("");
            response.ChangeStatus("exception");
            response.UpdateError(ex.toString());
        }
        finally{
           this.dataSource.Close();
        }
    }
    
    
    public void Delete(int userId){
        
        try{
           this.dataSource.Open();
           this.dataSource.BeginTransaction();
           OTS.DataModels.User user= (OTS.DataModels.User)dataSource.Find(OTS.DataModels.User.class, new Integer(userId));
           this.dataSource.Delete(user);
           this.dataSource.Delete(user.getUseraccount());
           this.dataSource.Commit();
           response.UpdateID(userId);
           response.ChangeContent("");
           response.ChangeStatus("ok");
           
        }
        catch(Throwable ex){
            this.dataSource.Rollback();
            response.UpdateID(0);
            response.ChangeContent("");
            response.ChangeStatus("exception");
            response.UpdateError(ex.toString());
        }
        finally{
           this.dataSource.Close();
        }
    }
    
    public void Save(UserAccountItem userAccount){
          if(userAccount.Id<=0){
              this.CreateUser(userAccount);
          }
          else{
              this.UpdateUser(userAccount);
          }
    }
    
    public void SaveBatch(UserAccountItem[] items){
       try{
            for(UserAccountItem a:items){
            this.Save(a);
          }
        response.ChangeContent("");
        response.ChangeStatus("ok");
       }
       catch(Throwable ex){
        response.UpdateID(0);
        response.ChangeContent("");
        response.ChangeStatus("exception");
        response.UpdateError(ex.toString());
       }
       
    }
    
    protected void CreateUser(UserAccountItem userAccount){
        try{
        this.dataSource.Open();
        this.dataSource.BeginTransaction(); 
         Useraccount ua= new Useraccount();
         ua.setUserName(userAccount.Email);
         
         String password=randomString(CHARSET_AZ_09,5);
         userAccount.Password=password;
         ua.setPassword(password);
         ua.setIsLocked(Boolean.FALSE);
         this.dataSource.Save(ua);
         
        Usertype ut= (Usertype)dataSource.Find(Usertype.class, new Integer(userAccount.UserTypeId));
        OTS.DataModels.User user= new OTS.DataModels.User(ua,userAccount.FirstName,userAccount.LastName);
        user.setEmail(userAccount.Email);
        user.setPhone(userAccount.Phone);
        user.setUsertype(ut);
        this.dataSource.Save(user);
        userAccount.Id=user.getUserId();
        this.dataSource.Commit();
        response.UpdateID(user.getUserId());
        response.ChangeContent(new Gson().toJson(userAccount));
        response.ChangeStatus("ok");
        }
        catch(Throwable ex){
        response.UpdateID(0);
        response.ChangeContent("");
        response.ChangeStatus("exception");
        response.UpdateError(ex.toString());
        this.dataSource.Rollback();
        }
        finally{
          this.dataSource.Close();
        }
    }
    
    
    protected void UpdateUser(UserAccountItem userAccount){
      
        try{
        
       this.dataSource.Open();
       this.dataSource.BeginTransaction();
      
        OTS.DataModels.User user= (OTS.DataModels.User)dataSource.Find(OTS.DataModels.User.class, new Integer(userAccount.Id));
       // user.setFirstName(userAccount.FirstName);
       // user.setFirstName(userAccount.LastName);
        
        user.setEmail(userAccount.Email);
        user.setPhone(userAccount.Phone);
        this.dataSource.Update(user);
        
        Useraccount ua=(Useraccount)this.dataSource.Find(Useraccount.class, new Integer(user.getUseraccount().getUserAccountId()));
        ua.setUserName(user.getEmail());
        this.dataSource.Commit();
        response.UpdateID(user.getUserId());
        response.ChangeContent("");
        response.ChangeStatus("ok"); 
        }
        catch(Throwable ex){
         response.UpdateID(0);
         response.ChangeContent("");
         response.ChangeStatus("exeception"); 
         response.UpdateError(ex.toString());
         this.dataSource.Rollback();
        }
        finally{
            this.dataSource.Close();
        }
    }
  
}
