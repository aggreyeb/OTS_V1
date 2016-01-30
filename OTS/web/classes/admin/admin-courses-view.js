var OTS=OTS||{};
OTS.Views.AdminCoursesView=function(courseManagementViewModel){
   var me=this;
   
   var name="Courses";
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
       $("#view-test1").show();
    };
    
    me.hide=function(){
        $("#view-test1").hide();
    };
    
      me.loadView=function(){
        var courses=[];
        
        /*
       $.post('CourseServlet',{action:"ListAvailableCourse"},function(msg){
              try{
              var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               for(var i=0;i<contents.length;i++){
                    courses.push(contents[i]);
                }   
            }catch(ex){ 
                alert(ex);
            }
        }).complete(function(){
           courseManagementView.LoadView(courses);
        });
       */
        $.get("templates/admin/admin-courses.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
        })
       
    };
    
      me.makeMenuActive=function(){
        $("lnk-courses").removeClass("active");
        $("#lnk-courses").addClass("active");
    };
    
     me.makeMenuInActive=function(){
        $("#lnk-courses").removeClass("active");
     
    };
}
