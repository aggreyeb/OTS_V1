/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels.TestAlgorithms;

import OTS.DataModels.Node;
import OTS.DataModels.NodeItem;
import OTS.ObjectModels.TestGenerationInput;
import OTS.ObjectModels.TestItemGenerationOutput;

/**
 *
 * @author aggreeb
 */
  interface IBuildItemGenerationOutput {
    TestItemGenerationOutput BuildOuput(NodeItem node, TestGenerationInput input);
    TestItemGenerationOutput BuildOuput(Node node, TestGenerationInput input);
}
