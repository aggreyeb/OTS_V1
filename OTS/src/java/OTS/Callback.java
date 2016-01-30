/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

import OTS.ObjectModels.Response;

/**
 *
 * @author MEA
 */
public interface Callback {
    void OnSucces(int id,String state);
     void OnSucces(int id,String state,String content);
    void OnFailure(int id,String failState,String message);
    void  WriteDescription(Message response);
}
