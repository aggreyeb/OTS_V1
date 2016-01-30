/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.Response;
import OTS.ObjectModels.UserAccountItem;
import OTS.ObjectModels.Users;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class UserManagementTest {
  
      @Test
    public void CreateAdminUser(){
         MySqlDataSource db=  new MySqlDataSource();
        Response message= new Response("","");
        Users users= new Users(message,db);
        UserAccountItem item= new UserAccountItem();
         item.Email="ad@ots.com";
         item.FirstName="Maggie";
         item.LastName="Lana";
         item.Phone="7809087865";
         item.UserTypeId=1; //
         users.Save(item);
         System.out.print(message.ToJson());
    }
    
    
    
    @Test
    public void CreateNewStudentUser(){
         MySqlDataSource db=  new MySqlDataSource();
        Response message= new Response("","");
        Users users= new Users(message,db);
        UserAccountItem item= new UserAccountItem();
         item.Email="test@ots.com";
         item.FirstName="John";
         item.LastName="Smitty";
         item.Phone="4039087865";
         item.UserTypeId=2; //
         users.Save(item);
         System.out.print(message.ToJson());
    }
    
     @Test
    public void UpdateStudentUser(){
         MySqlDataSource db=  new MySqlDataSource();
        Response message= new Response("","");
        Users users= new Users(message,db);
        UserAccountItem item= new UserAccountItem();
         item.Id=3;
         item.Email="test1@ots.com";
         item.Phone="7089087855";
         item.UserTypeId=2; //
         users.Save(item);
         System.out.print(message.ToJson());
    }
    
   @Test
    public void DeleteStudentUser(){
         MySqlDataSource db=  new MySqlDataSource();
        Response message= new Response("","");
        Users users= new Users(message,db);
        int id=20;
         users.Delete(id);
         System.out.print(message.ToJson());
    }
    
    @Test
    public void ListUsers(){
         MySqlDataSource db=  new MySqlDataSource();
        Response message= new Response("","");
        Users users= new Users(message,db);
        int userType=3; //Teachers
         users.ListUser(userType);
       System.out.print(message.ToJson());
    }
    
    
     @Test
    public void ResetPassword(){
        MySqlDataSource db=  new MySqlDataSource();
        Response message= new Response("","");
        Users users= new Users(message,db);
        int userId=29;
        users.ResetPassword(userId);
        System.out.print(message.ToJson());
    }
    
    
}
