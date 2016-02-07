var OTS=OTS||{};
OTS.Views.StudentMyTestView=function(){
   var me=this;
   var name="My Tests";
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
        $.get("templates/student/student-mytest.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("#lnk-Student-MyTest").removeClass("active");
        $("#lnk-Student-MyTest").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-Student-MyTest").removeClass("active");
     
    };
}
