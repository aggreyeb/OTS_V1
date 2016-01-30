var OTS=OTS||{};
OTS.Views.AdminKnowledMapsView=function(){
   var me=this;
   var name="Knowledge Maps";
   me.menuClicked=function(menuName){
         //alert("Show");
         if(name===menuName.trim()){
            me.makeMenuActive();
            me.loadView();  
            me.show();
         }else{
             me.makeMenuInActive();
         }
   };
    me.show=function(){
       $("#view-test1").show();
    };
    
    me.hide=function(){
        $("#view-test1").hide();
    };
    
      me.loadView=function(){
        $.get("templates/admin/admin-all-knowledgemaps.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("lnk-knowledgemaps").removeClass("active");
        $("#lnk-knowledgemaps").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-knowledgemaps").removeClass("active");
     
    };
}
