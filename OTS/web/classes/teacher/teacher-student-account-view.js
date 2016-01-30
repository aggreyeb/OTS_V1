var OTS=OTS||{};
OTS.Views.StudentAccountView=function(){
   var me=this;
   var name="Student Accounts";
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
       $("#view-student-accounts").show();
    };
    
    me.hide=function(){
        $("#view-student-accounts").hide();
    };
    
      me.loadView=function(){
        $.get("templates/teacher/teacher-studentaccounts.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
     me.makeMenuActive=function(){
        $("lnk-studentaccounts").removeClass("active");
        $("#lnk-studentaccounts").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-studentaccounts").removeClass("active");
     
    };
}
