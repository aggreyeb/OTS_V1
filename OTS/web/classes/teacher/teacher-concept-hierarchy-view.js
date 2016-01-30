var OTS=OTS||{};
OTS.Views.ConceptHierarchyView=function(){
   var me=this;
   var name="Concept Hierarchy";
   me.menuClicked=function(menuName){
       
         if(name===menuName.trim()){
        
           me.show();
            
         }
         else{
         
         }
   };
   
    me.show=function(){
       $("#view-concept-hierarchy").show();
    };
    
    me.hide=function(){
        $("#view-concept-hierarchy").hide();
    };
    
    
}
