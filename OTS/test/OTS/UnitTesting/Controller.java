/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.UnitTesting;

import OTS.DataModels.MySqlDataSource;
import OTS.*;
import OTS.ObjectModels.*;


/**
 *
 * @author MEA
 */
public class Controller {
   
   private  University university = new  PublicUniversity(new MySqlDataSource());
   
    public void CreateRole( PersonName name, Credential credential,int roleTypeId){
        Response response= new Response("","");
        Account account = new RoleAccount(name,roleTypeId,response);
        account.Create(this.university,credential);
        System.out.print(response.ToJson());
    }
    
    public void Login(Credential credential,Response response){
      
       Account account=new RoleAccount(response);
       account.Login(university, credential);
      
    }
    
    
    public void CreateBatchAccounts( RoleCredential[] credentials,Response response){
      
        Account account = new RoleAccount(response);
        account.CreateBatch(this.university,credentials);
        System.out.print(response.ToJson());
    }

    void ResetPassword(Credential credential,String newPassward,Response response) {
        Account account = new RoleAccount(response);
        account.ResetPassword(university, credential, newPassward);
        System.out.print(response.ToJson());
    }
}
