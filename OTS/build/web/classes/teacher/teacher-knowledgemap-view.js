var OTS=OTS||{};
OTS.Views.KnowledgeMapView=function(){
   var me=this;
   var name="Knowledge Maps";
   me.menuClicked=function(menuName){
      
         if(name===menuName.trim()){
           me.makeMenuActive();
           me.loadView();
           me.show();
         }else{
           me.makeMenuInActive();   
         }
   };
   
 
    me.show=function(){
       $("#view-container").show();
    };
    
    me.hide=function(){
        $("#view-container").hide();
    };
    
     me.loadView=function(){
        $.get("templates/teacher/teacher-knowledgemaps-list.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("#lnk-knowledgemaps").removeClass("active");
        $("#lnk-knowledgemaps").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-knowledgemaps").removeClass("active");
     
    };
    /*
    me.loadForm=function(){
         $.get("templates/teacher/teacher-knowledgemap-form.html",function(msg){
           $("#view-content-form").empty();
           $("#view-content-form").append(msg);
        });
    };
    */
}


