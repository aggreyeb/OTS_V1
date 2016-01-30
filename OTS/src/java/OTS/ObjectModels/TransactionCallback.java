/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.Callback;
import OTS.Message;

/**
 *
 * @author MEA
 */
public class TransactionCallback implements Callback {
    private int id;
    private String error="";
    private String state;
    private String content;
    private String identity;
    
    @Override
    public void OnSucces(int id,String state) {
       this.id=id;
       this.state=state;
    }

    @Override
    public void OnFailure(int id,String failState,String message) {
       this.state=failState;
       this.error=message;
       this.id=id;
    }
    
    public void ShowId(int[] buffer){
        buffer[0]=this.id;
    }
    
    public void DisplayError(String[] error ){
       error[0]=this.error;
    }
    
    public void DisplayState(String[] state){
      state[0]=this.state;
    }

    public Boolean HasError() {
       return error.length()>0;
    }
    
    public void WriteDescription(Message response){
            response.UpdateError(error);
            response.ChangeStatus(state);
            response.ChangeContent(content);
            response.UpdateIdentity(this.identity); 
            response.UpdateID(this.id);         
    }

    @Override
    public void OnSucces(int id, String state, String content) {
       this.id=id;
       this.state=state;
       this.content=content;
    }

  
}
