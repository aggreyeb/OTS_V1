var OTS=OTS||{};
OTS.Views.WelcomeView=function(){
   var me=this;
   var name="Home";
   me.menuClicked=function(menuName){
         //alert("Show");
         if(name===menuName.trim()){
             me.show();
             me.makeMenuActive();
             me.loadView();
         }
         else{
             me.makeMenuInActive();
         }
     }
    me.show=function(){
       $("#view-container").show();
    };
    
    me.hide=function(){
        $("#view-container").hide();
    };
    
       me.makeMenuActive=function(){
        $("#lnk-home").removeClass("active");
        $("#lnk-home").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-home").removeClass("active");
     
    };
    
      me.loadView=function(){
        $.get("templates/teacher/welcome.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
}
