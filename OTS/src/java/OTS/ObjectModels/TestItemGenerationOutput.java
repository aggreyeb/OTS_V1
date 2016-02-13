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
public class TestItemGenerationOutput {
    public String Text;
    public  List<String> LineItems;
    public Boolean IsCollection=false;
    public Boolean HasErrors=false;

    public TestItemGenerationOutput() {
        this.LineItems= new ArrayList();
    }

    @Override
   public String toString(){
       
       String items="";
       for(String s:LineItems){
       
           items+=s + "\n";
       
       }
       return this.Text + "\n" + "-----------" +"\n" +  items;
   }
}
