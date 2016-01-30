var OTS=OTS||{};
OTS.Views.StudentRegisteredCoursesView=function(){
   var me=this;
   var name="Registered Courses";
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
        $.get("templates/student/student-registered-courses.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
      me.makeMenuActive=function(){
        $("lnk-RegisteredCourses").removeClass("active");
        $("#lnk-RegisteredCourses").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-RegisteredCourses").removeClass("active");
     
    };
}
