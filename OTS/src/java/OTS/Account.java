/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

/**
 *
 * @author MEA
 */
public abstract class Account {
    public abstract void Create(University university,Credential credential);
    public abstract void CreateBatch(University university, RoleCredential[] credentials);
    public abstract void Login(University university,Credential credential);
    public abstract void Logout(Session session,Identity identity);
    public abstract void ResetPassword(University university,Credential credential,String newPassword); 
}
