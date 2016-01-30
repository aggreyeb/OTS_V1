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
public abstract class University {
    public abstract void CreateDepartment(String name,Callback callback);
    public abstract void CreateBatchAccounts(RoleCredential[] credentials,Callback callback);
    public abstract void CreateAccount(Credential credential, PersonName name,int roleTypeId,Callback callback);
    public abstract void AssignRoleDepartment(Identity identity,int departmentId,Callback callback);
    public abstract void UnAssignRoleDepartment(Identity identity,int departmentId,Callback callback);
    public abstract void Authenticate(Credential credential,Callback callback);
    public abstract void ResetPassward(Credential credential,String newPassword, Callback callback);
}
