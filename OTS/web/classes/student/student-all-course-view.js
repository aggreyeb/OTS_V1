var OTS=OTS||{};
OTS.Views.StudentAllCoursesView=function(){
   var me=this;
   var name="Courses";
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
        $.get("templates/student/student-all-courses.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("lnk-courses").removeClass("active");
        $("#lnk-courses").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-courses").removeClass("active");
     
    };
}
