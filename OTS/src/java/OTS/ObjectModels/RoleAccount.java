/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;
import OTS.*;
/**
 *
 * @author MEA
 */
public class RoleAccount extends Account {
    private int id;
    private PersonName personName;
    private int roleTypeId;
    private Message message;
    
    public RoleAccount(PersonName personName,int roleTypeId,Message message) {
        this.personName = personName;
        this.roleTypeId=roleTypeId;
        this.message=message;
    }
    
    public RoleAccount(Message message){
       this.message=message;
    }
    @Override
    public void Create(University university, Credential credential) {
         TransactionCallback tcb= new TransactionCallback();
         university.CreateAccount(credential, this.personName, this.roleTypeId,tcb);
         this.CreateResponseMessage(tcb);
    }

    @Override
    public void CreateBatch(University university, RoleCredential[] credentials) {
       TransactionCallback tcb= new TransactionCallback();
        university.CreateBatchAccounts( credentials, tcb);
        this.CreateResponseMessage(tcb);
        
    }

    @Override
    public void Login(University university, Credential credential) {
        TransactionCallback tcb= new TransactionCallback();
        university.Authenticate(credential, tcb);
        this.CreateResponseMessage(tcb);
    }

    @Override
    public void Logout(Session session, Identity identity) {
        
    }
    
    
    @Override
    public void ResetPassword(University university, Credential credential, String newPassword) {
        TransactionCallback tcb= new TransactionCallback();
        university.ResetPassward(credential,newPassword, tcb);
        this.CreateResponseMessage(tcb);
    }
    
    
    protected void CreateResponseMessage(TransactionCallback tcb){
       int[] id= new int[1];
            String[] status=new String[1];
            tcb.ShowId(id);
            tcb.DisplayState(status);
            this.message.ChangeContent("");
            this.message.ChangeStatus(status[0]);
          if(tcb.HasError()){
             String[] error= new String[1];
              tcb.DisplayError(error);
              this.message.UpdateError(error[0]);
          }
    }

  
}
