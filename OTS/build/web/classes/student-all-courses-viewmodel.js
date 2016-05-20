var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.StudentAllCourses=function(){
    var me=this;
    me.courseList=ko.observableArray([]);
    me.CheckAll=ko.observable(false);
    
    me.CanEnableRegister=ko.computed(function(){
        return  me.courseList().length>0;
    });
    
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
    
    $(function(){
        
        $.post("CourseServlet",{action:"ListAllCourses"},function(msg){
            try{
               var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               if(contents.length>0){
               for(var i=0;i<contents.length;i++){
                    contents[i].selected=false;
                    me.courseList.push(contents[i]);
                }  
              } 
            }catch(ex){
                
                alert(ex);
            }
        });
    });
    
      me.CheckAllItems=function(){
      
        var state=me.CheckAll();
        me.ToogleQuestionBankItemsSelection(state);
    };
    
     me.ToogleQuestionBankItemsSelection=function(state){
     var items= ko.toJS(me.courseList());
     for(var i=0;i<items.length;i++){
          items[i].selected=state
     }
     me.courseList([]);
      for(var i=0;i<items.length;i++){
          me.courseList.push(items[i]);
     }
  };
    
   me.LoadAllCourse=function(){
        $.post("CourseServlet",{action:"ListAllCourses"},function(msg){
            try{
               me.courseList([]);
               var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               if(contents.length>0){
               for(var i=0;i<contents.length;i++){
                    contents[i].selected=false;
                    me.courseList.push(contents[i]);
                }  
              } 
            }catch(ex){
                
                alert(ex);
            }
        }); 
   } ;
  me.onRegister=function(){
   //alert(JSON.stringify(me.ListSelectedCourses()));  
     var data=JSON.stringify(me.ListSelectedCourses());
    $.post("CourseServlet",{action:"RegisterCourses",courses:data},function(msg){
            try{
             try{ 
                var message =JSON.parse(msg);
                
                  if(message.response.status==="ok"){
                     
                     me.LoadAllCourse();
              
                    me.form.headingVisisble(false);
                    me.form.responseDialog(me.form.dialogType.SUCCESS);
                    me.form.responseMessageText("Operation done successfully");
                    me.form.responseBoxStyle("alert alert-success");
                    me.form.responseMessageVisible(true); 
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-info");
                    me.form.responseMessageVisible(true); 
                }
             
                }
                catch(error){
                    
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(error);
                    me.form.responseBoxStyle("alert alert-info");
                    me.form.responseMessageVisible(true); 
                }
               
            }catch(ex){
                
                alert(ex);
            }
        });
  };
  
  me.ListSelectedCourses=function(){
     var list = ko.toJS(me.courseList);
      var  courses=[];
      for(var i=0;i<list.length;i++){
          if(list[i].selected===true){
              courses.push(list[i]);
          }
      }
      return courses;
  };
};

