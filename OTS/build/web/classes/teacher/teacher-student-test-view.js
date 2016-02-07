var OTS=OTS||{};
OTS.Views.StudentTestsView=function(){
   var me=this;
   var name="Student Tests";
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
       $("#lnk-student-tests").show();
    };
    
    me.hide=function(){
        $("#lnk-student-tests").hide();
    };
    
      me.loadView=function(){
        
          $.get("templates/teacher/teacher-student-tests.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("#lnk-student-tests").removeClass("active");
        $("#lnk-student-tests").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-student-tests").removeClass("active");
     
    };
}
