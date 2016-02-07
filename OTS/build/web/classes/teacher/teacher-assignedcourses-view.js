var OTS=OTS||{};
OTS.Views.AssignedCoursesView=function(){
   var me=this;
   var name="Assigned Courses";
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
   
    me.makeMenuActive=function(){
        $("#lnk-assigned-courses").removeClass("active");
        $("#lnk-assigned-courses").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-assigned-courses").removeClass("active");
     
    };
    me.show=function(){
       $("#view-container").show();
    };
    
    me.hide=function(){
        $("#view-container").hide();
    };
    
    me.loadView=function(){
        $.get("templates/teacher/courses.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        });
    };
    
    me.onSubmit=function(){
          var test=$("#txt-test").val();
          alert(test);
    };
}

