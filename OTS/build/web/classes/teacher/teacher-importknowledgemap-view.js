var OTS=OTS||{};
OTS.Views.ImportKnowledgeMapView=function(){
   var me=this;
   var name="Import Knowledge Maps";
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
       $("#view-importknowledgemaps").show();
    };
    
    me.hide=function(){
        $("#view-importknowledgemaps").hide();
    };
    
      me.loadView=function(){
        $.get("templates/teacher/teacher-import-knowledgemap.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("lnk-importknowledges").removeClass("active");
        $("#lnk-importknowledges").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-importknowledges").removeClass("active");
     
    };
}

