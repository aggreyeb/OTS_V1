/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.ObjectModels;

import OTS.KnowledgeMapId;
import OTS.TeacherId;

/**
 *
 * @author MEA
 */
public class KnowledgeMap {
     KnowledgeMapId knowledgeMapId;
     KnowledgeMaps knowledgeMaps;

    public KnowledgeMap(KnowledgeMapId knowledgeMapId, KnowledgeMaps knowledgeMaps) {
        this.knowledgeMapId = knowledgeMapId;
        this.knowledgeMaps = knowledgeMaps;
    }

 
   
   public Boolean Exit(){
     return knowledgeMaps.HasKnowledgeMap(this.knowledgeMapId);
   }
    
}
