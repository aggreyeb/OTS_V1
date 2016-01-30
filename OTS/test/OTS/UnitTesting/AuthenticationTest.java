/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.UnitTesting;

import OTS.Account;
import OTS.Credential;
import OTS.DataModels.MySqlDataSource;
import OTS.Message;
import OTS.ObjectModels.PublicUniversity;
import OTS.ObjectModels.Response;
import OTS.ObjectModels.RoleAccount;
import OTS.University;
import static com.mchange.v2.c3p0.impl.C3P0Defaults.password;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class AuthenticationTest {
   
    @Test
    public void Login(){
    
        String userName="maiga";
       String password="maiga";
         Message message= new Response("","");
        University university= new PublicUniversity(new MySqlDataSource());
         Account account=new RoleAccount(message);
        Credential credential= new Credential(userName,password);
        account.Login(university, credential);
        System.out.print(message.ToJson());
    }
    
}
