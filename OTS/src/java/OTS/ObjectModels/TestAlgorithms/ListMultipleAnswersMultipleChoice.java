/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.TestAlgorithms;

import OTS.DataModels.DataSource;
import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;

/**
 *
 * @author aggreeb
 */
public class ListMultipleAnswersMultipleChoice extends CompositeAlgorithm{
     private String myName="ListMultipleChoice-MultipleAnwsersCorrect";
    public ListMultipleAnswersMultipleChoice(DataSource dataSource, String name) {
        super(dataSource, name,"");
        this.Name=myName;
    }

    @Override
    protected TestItemGenerationOutput BuildOuput(NodeItem node, TestGenerationInput input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected TestItemGenerationOutput BuildOuput(Node node, TestGenerationInput input) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
