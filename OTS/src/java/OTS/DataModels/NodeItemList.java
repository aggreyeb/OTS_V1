/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package OTS.DataModels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author MEA
 */

public class NodeItemList {
   private List<NodeItem> list;

    public NodeItemList() {
        this.list = new ArrayList();
    }
    
    public void Add(NodeItem item){
        this.list.add(item);
    }
    
    public void Remove(NodeItem item){
        this.list.remove(item);
    }
    
    public void AddRange(NodeItemList items){
        this.list.addAll(items.list);
    }
    
    public void Clear(){
      this.list.clear();
    }

    public Boolean Contains(NodeItem item){
       return this.list.contains(item);
    }
    
   public NodeItem Find(NodeItem item,String identity){
     
       
        if(item.Identity.equals(identity))
                 return item;
       NodeItem result=null;
       for(NodeItem a:this.list)
       {
            if(a.Identity.equals(identity)){
                 result=a;
                 break;
            }
           result  =a.Find(identity);
           if(result !=null)
               return result;
        }
        return null;
   }

   
   public NodeItem Find(String identity) {
      NodeItem result=null;
       for(NodeItem a:this.list)
       {
           if(a.Identity.equals(identity)){
               result=a;
               break;
           }
           if(a.HasChildren()){
             result= a.Find(identity);
              if(result !=null)
               return result;
           }
      
        }
        return result;
     
   }  

    void Update(int newId) {
         for(NodeItem item :this.list){
              if(item.HasChildren()){
                 item.UpdateRootId(newId);
              }
              else{
                 item.RootId=newId;
             
              }
         }
    }
   
   
    public void ListNodeItems(Collection<NodeItem> items){
         items.clear();
         items.addAll(list);
    
    }
   
    public int Count(){
       return this.list.size();
    }
}
