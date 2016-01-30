/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.UnitTesting;

import OTS.Credential;
import OTS.ObjectModels.Response;
import OTS.PersonName;
import OTS.RoleCredential;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author MEA
 */
public class ControllerTest {
    
     @Test
    public void CreateAccount(){
        Controller controller= new Controller();
        PersonName name= new PersonName("Ebenezer","","Aggrey");
        Credential credential = new Credential("eb","eb");
        controller.CreateRole(name, credential, 3);
    }
    
     @Test
    public void AuthenticateAccount(){
   
        Response  m= new Response("",""); 
        Controller controller= new Controller();
        Credential cr= new Credential("eb","eb");
        controller.Login(cr,m);
         System.out.print(m.ToJson());
    }
    
   @Test
    public void CreateBatchAccount(){
        Response  m= new Response("",""); 
        PersonName name3= new PersonName("FName1","MName1","LName1");
        PersonName name4= new PersonName("FName2","MName2","LName2");
        RoleCredential c1= new RoleCredential(new Credential("user5","password7"),1,name3 );
        RoleCredential c2= new RoleCredential(new Credential("user6","password8"),1,name4 );
        List<RoleCredential> list= new ArrayList();
        list.add(c1);
         list.add(c2);
         RoleCredential[] c= new RoleCredential[list.size()];
         list.toArray(c);
         Controller controller= new Controller();
         controller.CreateBatchAccounts(c,m);
    }
    
    @Test
    public void ResetAccountPassword(){
   
        Response  m= new Response("",""); 
        Controller controller= new Controller();
        Credential cr= new Credential("UserName","myNewPassword");
        controller.ResetPassword(cr,"NewPassword",m);
        System.out.print(m.ToJson());
    }
    
    
     @Test
    public void Response(){
   
        Response  r= new Response("",""); 
        r.ChangeContent("test");
        r.ChangeStatus("ok");
       
        System.out.print(r.ToJson());
         System.out.print("\n");
         System.out.print(r.ToXml());
         
    }
    
}
