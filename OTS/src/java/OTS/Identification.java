/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS;

import OTS.ObjectModels.UserId;

/**
 *
 * @author MEA
 */
public abstract class Identification {
      public abstract void  Update(UserId userId, int userTypeId);
      public abstract void  Update(UserId userId, int userTypeId, int departamentId);
}
