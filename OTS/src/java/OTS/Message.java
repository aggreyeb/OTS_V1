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
public abstract class Message implements ISerializable{
    public abstract void UpdateError(String error);
    public abstract void ChangeContent(String content);
    public abstract void ChangeStatus(String status);
    public abstract void UpdateID(int id);
    public abstract void UpdateIdentity(String identity);
}
