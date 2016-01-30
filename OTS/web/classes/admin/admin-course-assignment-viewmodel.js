var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.TeacherCourseAssigment=function(){
    var me=this;
    me.teacherCouresList=ko.observableArray([]);
    me.courses=ko.observableArray([]);
    me.selectedUnAssignedCourses=ko.observable();
    me.TeacherCourseAssignement={
       Teacher:{}
   };
   
    me.responseMessageVisible=ko.observable(false);
    me.responseBoxStyle=ko.observable("");
    me.responseDialog=ko.observable("");
    me.responseMessageText=ko.observable("");
    me.closeMessageBox=function(e){
        $("#lnk-hideMessageBox").hide();
    };
    
    me.DisplaySuccessMessage=function(){
        me.responseMessageVisible(true);
        me.responseBoxStyle("alert-success");
        me.responseDialog("Success!");
        me.responseMessageText("Course assigned");
    };
    
     me.DisplayFailMessage=function(){
        me.responseMessageVisible(true);
        me.responseBoxStyle("alert-danger");
        me.responseDialog("Fail!");
        me.responseMessageText("Unable to assigned course");
    };
    
    me.DisplayExceptionMessage=function(){
        
    };
   
   
   me.onUnAssignCourse=function(item,event){
       var teacherId=me.TeacherCourseAssignement.Teacher.Id;
       var courseId= item.Id;
      
       $.post("CourseServlet",{action:"UnAssignTeacherCourse", TeacherId:teacherId,CourseId: courseId},function(msg){
             var message =JSON.parse(msg);
              if(message.response.status==="ok"){
                 me.teacherCouresList.remove(item);
                 me.courses.push(item);
                 me.DisplaySuccessMessage();
                   
              }
              else{
                  me.DisplayFailMessage();
              }
           
       });
   };
   
   me.onAssign=function(){
       var teacherId=me.TeacherCourseAssignement.Teacher.Id;
       var courseId= me.selectedUnAssignedCourses().Id;
       var item=me.selectedUnAssignedCourses()
       $.post("CourseServlet",{action:"AssignTeacherCourse", TeacherId:teacherId,CourseId: courseId},function(msg){
           var message =JSON.parse(msg);
            if(message.response.status==="ok"){
               me.courses.remove(me.selectedUnAssignedCourses());
               me.teacherCouresList.push(item);
               me.DisplaySuccessMessage();
              }
              else{
                   me.DisplayFailMessage();
              }
           
       }).complete(function(){
           
       });
   };
   
   me.LoadTeacherUnassignedCourses=function(item){
       $.post("CourseServlet",{action:"ListTeacherUnAssignedCourses", TeacherId:item.Id},function(msg){
        
           me.courses([]);
          var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
            
                 me.courses.push(contents[i]);
            }
         }); 
   };
   
   me.LoadTeacherCourseList=function(item){
        $.post("CourseServlet",{action:"ListCoursesByTeacher", TeacherId:item.Id},function(msg){
         me.teacherCouresList([]);
          var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
            
                 me.teacherCouresList.push(contents[i]);
            }
          });   
    };
   
    me.LoadView=function(item,teacherCourseList,courses){
         me.TeacherCourseAssignement.Teacher=item;
         for(var i=0;i<teacherCourseList.length;i++){
             me.teacherCouresList.push(teacherCourseList[i]); 
         }
       
         for(var i=0;i<courses.length;i++){
             me.courses.push(courses[i]);
         };
         
       $.get("templates/admin/admin-course-assignment.html",{},function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
           $("#lbl-selected-menuitem").text("Accounts- Teacher Course Assignment" + "("+ "Dr " + item.FirstName + " " + item.LastName + ")" );
          
       }).complete(function(){
          //  var view = new OTS.ViewModels.TeacherCourseAssigment();
            ko.applyBindings(me,$("#view-content")[0]);
       });
   
    };
};

