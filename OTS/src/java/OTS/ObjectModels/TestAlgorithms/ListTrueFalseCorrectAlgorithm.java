/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.TestAlgorithms;

import OTS.DataModels.DataSource;
import OTS.ObjectModels.ConceptRelationMap;

/**
 *
 * @author MEA
 */
public class ListTrueFalseCorrectAlgorithm extends TrueFalseAlgorithm{

    public ListTrueFalseCorrectAlgorithm(DataSource dataSource, String name, ConceptRelationMap conceptRelationMap) {
        super(dataSource, name, conceptRelationMap);
        this.Name=name;
    }
   
}
