var OTS=OTS||{};
OTS.Views.TestsView=function(){
   var me=this;
   var name="Tests";
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
       $("#view-test").show();
    };
    
    me.hide=function(){
        $("#view-test").hide();
    };
    
      me.loadView=function(){
        
          $.get("templates/teacher/teacher-tests.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("lnk-tests").removeClass("active");
        $("#lnk-tests").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-tests").removeClass("active");
     
    };
}
