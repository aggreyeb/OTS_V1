
var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.CourseManagementViewModel=function(){
    var me=this;
    var selectedCourse={};
    me.MyCourses=ko.observableArray([]);
    
    me.ActionType={
        New:"New",Edit:"Edit",Delete:"Delete"
    };
    
    me.SelectedActionType="";
    me.form={
        CourseTypeId:ko.observable(""),
        Number:ko.observable(),
        Name:ko.observable(),
        visible:ko.observable(false),
        heading:ko.observable(),
        responseMessageVisible:ko.observable(false),
        responseBoxStyle:ko.observable(""),
        closeMessageBox:function(){
            
        },
        responseDialog:ko.observable(""),
        responseMessageText:ko.observable(""),
        MyBuddyCourses:ko.observableArray([])
    };
    
    
    
    $(function(){
        
 
       $.post('CourseServlet',{action:"ListAvailableCourse"},function(msg){
              try{
              var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               for(var i=0;i<contents.length;i++){
                    me.MyCourses.push(contents[i]);
                }   
            }catch(ex){ 
                alert(ex);
            }
        }).complete(function(){
          
        });
       
    });
   
    me.onNew=function(e){
        me.form.CourseTypeId("");
        me.form.Name("");
        me.form.Number("");
        me.form.visible(true);
        me.form.heading("Create New");
        me.SelectedActionType=me.ActionType.NEW;
    };
    
    me.ListsAvailableCourses=function(){
         me.MyCourses([]);
         $.post('CourseServlet',{action:"ListAvailableCourse"},function(msg){
              try{
              var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               for(var i=0;i<contents.length;i++){
                    me.MyCourses.push(contents[i]);
                }   
            }catch(ex){ 
                alert(ex);
            }
        }).complete(function(){
          
        });
    };
    
    me.onEdit=function(item,event){
        selectedCourse=item;
        me.form.CourseTypeId(item.CourseTypeId);
        me.form.Name(item.Name);
        me.form.Number(item.Number);
        me.form.heading("Edit");
        me.form.visible(true);
        me.SelectedActionType=me.ActionType.Edit;
     
    };
    
     me.onDelete=function(item,event){
         selectedCourse=item;
         me.SelectedActionType=me.ActionType.Delete;
         var course={action:"Delete",Id:selectedCourse.CourseTypeId
                    
                    };
         $.post("CourseServlet",course,function(msg){
               var message =JSON.parse(msg);
               if(message.response.status==="ok"){
                   me.form.responseMessageVisible(true);
                   me.form.responseBoxStyle("alert-success");
                   me.form.responseDialog("Success!");
                   me.form.responseMessageText("Course Deleted");
               }
               else{
                   me.form.responseMessageVisible(true);
                   me.form.responseBoxStyle("alert-danger");
                   me.form.responseDialog("Fail!");
                   me.form.responseMessageText("Fail to delete course");
               }
          }).complete(function(){
              me.ListsAvailableCourses();
          });
    };
    
    
    me.onSubmitNew=function(event){
         var id=0;
         if(me.form.CourseTypeId() !==""){
             id=me.form.CourseTypeId();
         }
        
        var course={action:"Save",Id:id,
                     Number:me.form.Number,
                     Name:me.form.Name
                    };
          
          $.post("CourseServlet",course,function(msg){
               var message =JSON.parse(msg);
               if(message.response.status==="ok"){
                   me.form.responseMessageVisible(true);
                   me.form.responseBoxStyle("alert-success");
                   me.form.responseDialog("Success!");
                   me.form.responseMessageText("Course submitted");
                   me.form.visible(false);
               }
               else{
                   me.form.responseMessageVisible(true);
                   me.form.responseBoxStyle("alert-danger");
                   me.form.responseDialog("Fail!");
                   me.form.responseMessageText("Fail to submit course");
               }
          }).complete(function(){
              me.ListsAvailableCourses();
              
          });
     };
    
    me.onCancelNew=function(event){
          me.form.visible(false);
    };

};