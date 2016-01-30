/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import java.util.List;

/**
 *
 * @author MEA
 */
public interface ITestItemGeneration extends IGeneratable<TestGenerationInput,List<TestItemGenerationOutput>>  {
    public Boolean HasId(String name);
}
