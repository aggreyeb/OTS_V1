/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OTS.ObjectModels;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aggreeb
 */
public class TestItemGenerationOutputs extends TestItemGenerationOutput {

    List<TestItemGenerationOutput> items;
    public TestItemGenerationOutputs() {
       items= new ArrayList();
       this.IsCollection=true;
    }
    
    public void AddRange(List<TestItemGenerationOutput> outputs){
        items.addAll(outputs);
    }
    public void Add(TestItemGenerationOutput output){
        items.add(output);
    }
    
    public void Remove(TestItemGenerationOutput output){
        items.remove(output);
    }
    
    public void Clear(){
        items.clear();
    }
    
    public List<TestItemGenerationOutput> Items(){
        return items;
    }
}
