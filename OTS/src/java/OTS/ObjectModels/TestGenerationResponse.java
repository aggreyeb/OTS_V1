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
 * @author MEA
 */
public class TestGenerationResponse {
    public List<TestItemGenerationOutput> Items;
    public Boolean HasErrors=false;
    public String Error="";

    public TestGenerationResponse() {
       Items= new ArrayList();
    }
    
    
}
