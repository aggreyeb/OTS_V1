var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.StudentAllCourseTest=function(){
    var me=this;
    me.courseTestList=ko.observableArray([]);
    
    
     me.form={ 
      dialogType:{CRITICAL:"Critical !",SUCCESS: "Success !", FAIL :"Fail !"},
      formVisible:ko.observable(true),
      responseMessageVisible:ko.observable(false),
      responseDialog:ko.observable("Mock Page!"),
      responseMessageText:ko.observable("This is a mock page for review.Function not implemented"),
      responseBoxStyle:ko.observable("alert alert-info"),
      headingText:ko.observable(""),
      headingVisisble:ko.observable(false),
    
      closeMessageBox:function(event){
          me.form.responseMessageVisible(false);
      }
    };
    me.LoadRegisteredCourses=function(){
       
        $.post("CourseServlet",{action:"RegisteredCourseTest"},function(msg){
            try{
               
               var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               me.courseTestList([]);
               for(var i=0;i<contents.length;i++){
                   
                    me.courseTestList.push(contents[i]);
                }   
            }catch(ex){
                
                alert(ex);
            }
        });
    };
    
    $(function(){
        
        $.post("CourseServlet",{action:"RegisteredCourseTest"},function(msg){
            try{
               var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               for(var i=0;i<contents.length;i++){
                    me.courseTestList.push(contents[i]);
                }   
            }catch(ex){
                
                alert(ex);
            }
        });
    });
    
   me.onUnregister=function(item,event){
     //Implement  Take Test
     console.log(item);
      $.post("CourseServlet",{action:"UnRegisterCourse",StudentCourseId:item.StudentCourseId},function(msg){
            try{
                var message =JSON.parse(msg);
                 me.LoadRegisteredCourses();
            }catch(ex){
                
                alert(ex);
            }
        });
  };
 
};

