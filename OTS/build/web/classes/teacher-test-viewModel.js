
var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.Course=function(properties){
   var me=this;
   me.Id=properties.Id;
   me.Number=properties.Number;
   me.Name=properties.Name;
};

OTS.Time=function(){
    var me=this;
    me.isvalidTime=function(inputStr) {
    if (!inputStr || inputStr.length<1) {return false;}
    var time = inputStr.split(':');
    return time.length === 2 
           && parseInt(time[0],10)>=0 
           && parseInt(time[0],10)<=23 
           && parseInt(time[1],10)>=0 
           && parseInt(time[1],10)<=59;
   }
};


OTS.DateTime=function(){
    var me=this;
    me.isValidDate = function() {
  
     }
    
   };

OTS.ViewModels.TestViewModel=function(testGenerationViewModel){
    var me=this;
    me.testGenerationViewModel=testGenerationViewModel|| new OTS.ViewModels.TestGenerationViewModel();
    me.reviewTestQuestionViewModel= new OTS.ViewModels.ReviewTestQuestionsViewModel();
    me.myTitle=ko.observable("Node Information");
    me.mytestList=ko.observableArray([]);
    me.courseList=ko.observableArray([new OTS.Course({Id:1,Number:1,Name:"Test1"})]);
    me.selectedCourse=ko.observable();
    me.toggleNewTest=ko.observable(false);
    me.selectedTest=null;
    
    me.ListTeacherCourse=function(){
         $.post("TestGenerationServlet",{action:"ListTeacherCourse"},function(msg){
           
          if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                  var contents=JSON.parse(message.response.content);  
                  me.populateCourses(contents);
                  
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-info");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-info");
                me.form.responseMessageVisible(true); 
            }
         
       });
    };
    
   
     $(function(){
       
       $.post("TestGenerationServlet",{action:"ListTeacherCourse"},function(msg){
           
          if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                  var contents=JSON.parse(message.response.content);  
                  me.populateCourses(contents);
                  
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-info");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-info");
                me.form.responseMessageVisible(true); 
            }
         
       });
   
     });
     
   me.showValidationError=function(htmlText){
        $("#div-validation").show();
       $("#div-validation").html(htmlText);
   };   
   
   me.hideValidationError=function(){
         $("#div-validation").hide();
   };
  
     
     me.populateCouresTest=function(array){
         me.mytestList([]);
         var disableColor="silver";
          for(var i=0;i<array.length;i++){
           array[i].StartDate=me.dateToShort(array[i].StartDate);
           array[i].CanEnableDelete =false;
           
           if(array[i].IsActivated){
             array[i].Activated="Yes"  ;
             array[i].ActivatedStyle=disableColor ; 
             array[i].CanEnableDelete=false;
           }
           else{
                array[i].Activated="No" ; 
                array[i].ActivatedStyle=""  ;
                 array[i].IsAllMarked=false;
                array[i].ActivatedStyle="" ;
                array[i].CanEnableDelete=true;
           }
           
           if(array[i].IsAllMarked){
                array[i].MarkTestStyle=disableColor ; 
                array[i].CanEnableDelete=false;
           }
           else{
                array[i].MarkTestStyle=""; 
            
           }
             me.mytestList.push(array[i]);
         }
     };
     
   me.dateToShort=function(myDate) {

            
           // return  convertedStartDate.toLocaleDateString();
             var currentDt = new Date(myDate);
                var mm = currentDt.getMonth() + 1;
                var dd = currentDt.getDate();
                var yyyy = currentDt.getFullYear();
                var date = mm + '/' + dd + '/' + yyyy;
              return date;
          };
          
     me.populateCourses=function(array){
         me.courseList([]);
         for(var i=0;i<array.length;i++){
            me.courseList.push(array[i]);
         }
         
     };
     me.formVisible=ko.observable(false);
     
     me.onCourseChanged=function(){
       var id= me.selectedCourse();
       me.formVisible(false);
       me.form.responseMessageVisible(false); 
      
      if(id===undefined){
             me.mytestList([]);    
       }
       else{
        $.post("TestGenerationServlet",{action:"ListCourseTest",CourseId:id},function(msg){
          
            if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                  var contents=JSON.parse(message.response.content);  
                   me.populateCouresTest(contents);
                  
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-danger");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true); 
            }
                me.toggleNewTest(true);
        });
       }
     };
     
     me.onNewTest=function(event){
        if(me.selectedCourse()==undefined){
           
              me.toggleNewTest(false);
        }
        else{
               me.form.heading("Create New");
                me.form.reset();
               me.formVisible(true);
               me.toggleNewTest(true);
               me.form.canShowUpdateButton(false);
               me.form.canShowSubmitButton(true);
                me.form.responseMessageVisible(false); 
        }
       
     };
  
  
  
  me.onDeleteTest=function(item,event){
      me.form.responseMessageVisible(false); 
      if(!item.IsAllMarked & !item.IsActivated){ 
      me.form.responseMessageVisible(false);
      me.selectedTest=item;
      me.formVisible(false);
      $.post("TestGenerationServlet",{action:"DeleteTest",TestId:item.TestId},function(msg){
          
          if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                me.mytestList.remove(item);
                me.form.responseDialog("Success!");
                me.form.responseMessageText("Test Deleted");
                me.form.responseBoxStyle("alert alert-success");
                me.form.responseMessageVisible(true); 
                me.formVisible(false);
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-danger");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true); 
            }
      });
      }
      else{
                    me.form.responseDialog("Delete Test");
                    me.form.responseMessageText("Can not delete test already activated or marked");
                    me.form.responseBoxStyle("alert alert-info");
                    me.form.responseMessageVisible(true); 
      }
  };
  
  
        me.onUpdateTest=function(event){
          me.form.responseMessageVisible(false);
          me.hideValidationError();
          var  result=  me.validate();
          if(!result.hasError){
           var test={
               TestId:me.selectedTest.TestId,
               Name:me.form.name(),
               TotalMark:me.form.marks(),
               NumberOfQuestion:me.form.questions(),
               StartDate:null,
               StartTime:me.form.startTime(),
               EndTime:me.form.endTime(),
               IsActivated:me.form.isActivated()
           } ;
           
              $.post("TestGenerationServlet",{action:"ModifyTest",
               AcademicTest:JSON.stringify(test),StartDate:me.form.startdate()},function(msg){
               
                if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                /*
                var currentId= message.response.id;
                test.TestId=currentId;
                test.StartDate=me.form.startdate();
                test.CanEnableDelete =false;
                if(test.IsActivated){
                    test.Activated="Yes"
                }
                else{
                    test.Activated="No"
                }
                me.mytestList.replace(me.selectedTest,test);
                */
               me.onCourseChanged();
                me.form.responseDialog("Success!");
                me.form.responseMessageText("Test Updated");
                me.form.responseBoxStyle("alert alert-success");
                me.form.responseMessageVisible(true); 
                me.formVisible(false);
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-danger");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true); 
            }
           });
       }
       else{
             me.showValidationError(result.error);
           
       }
        };
        
       me.onSubmitNew=function(event){
         me.form.responseMessageVisible(false);
         me.hideValidationError();
         var  result=  me.validate();
         if(!result.hasError){
           var test={
               TestId:0,
               Name:me.form.name(),
               TotalMark:me.form.marks(),
               NumberOfQuestion:0,
               StartDate:null,
               StartTime:me.form.startTime(),
               EndTime:me.form.endTime(),
               IsActivated:me.form.isActivated()
           } ; 
           var courseId=me.selectedCourse();
           $.post("TestGenerationServlet",{action:"CreateNewTest",CourseId:courseId,
               AcademicTest:JSON.stringify(test),StartDate:me.form.startdate()},function(msg){
               
                if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
              
                 //Reload current course test 
                  me.onCourseChanged();

                me.form.responseDialog("Success!");
                me.form.responseMessageText("Test Created");
                me.form.responseBoxStyle("alert alert-success");
                me.form.responseMessageVisible(true); 
                me.formVisible(false);
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-danger");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true); 
            }
           });
           me.formVisible(false);
       }
       else{
               me.showValidationError(result.error);
       }
       
       };
       
        me.onCancelNew=function(event){
             me.formVisible(false);
             me.form.responseMessageVisible(false);
             me.hideValidationError();
       };
       
       me.onEditTest=function(item,event){
          me.form.fill(item);
          me.selectedTest=item;
          me.formVisible(true);
          me.form.heading("Edit");
          me.form.canShowUpdateButton(true);
          me.form.canShowSubmitButton(false);
          me.form.responseMessageVisible(false); 
         
       };
       
       
       me.onGenerateTest=function(item,event){
           
           var course={};
           for(var i=0;i<me.courseList().length;i++){
               if(me.courseList()[i].Id===me.selectedCourse()){
                   course=me.courseList()[i];
                   break;
               }
           }
           me.testGenerationViewModel.loadView(item,course);
       };
       
        me.onReviewTestQuetions=function(item,event){
         
           var course={};
           for(var i=0;i<me.courseList().length;i++){
               if(me.courseList()[i].Id===me.selectedCourse()){
                   course=me.courseList()[i];
                   break;
               }
           }
           me.reviewTestQuestionViewModel.loadView(item,course);
       };
       
       me.onActivateTest=function(item,event){
      
        if(!item.IsAllMarked){ 
        me.form.responseMessageVisible(true);   
        me.form.responseMessageVisible(false);
        me.selectedTest=item;
        me.formVisible(false);
        
      $.post("TestGenerationServlet",{action:"ActivateTest",TestId:item.TestId},function(msg){
          
          if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                /*
                  item.Activated="Yes";
                
                    var test={
                     TestId:me.selectedTest.TestId,
                     Name:me.selectedTest.Name,
                     TotalMark:me.selectedTest.TotalMark,
                     NumberOfQuestion:me.selectedTest.NumberOfQuestion,
                     StartDate:me.selectedTest.StateDate,
                     StartTime:me.selectedTest.StartTime,
                     EndTime:me.selectedTest.EndTime,
                     IsActivated:true,
                     Activated:"Yes",
                     CanEnableDelete:false
                 } ;
                    test.Activated="Yes";
                    test.CanEnableDelete =false;
                    test.ActivatedStyle ="";
                    test.IsAllMarked=false;
                    test.MarkTestStyle ="";
                me.mytestList.replace(me.selectedTest,test);
                */
               me.onCourseChanged();
                me.form.responseDialog("Success!");
                me.form.responseMessageText("Test Activated");
                me.form.responseBoxStyle("alert alert-success");
                me.form.responseMessageVisible(true); 
                me.formVisible(false);
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-danger");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("Activate Test");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true); 
            }
          });
     
           }
           else{
                    me.form.responseDialog("Activate Test");
                    me.form.responseMessageText("Can not activate test which has been marked");
                    me.form.responseBoxStyle("alert alert-info");
                    me.form.responseMessageVisible(true); 
           }
           
       };
       
        me.onDeActivateTest=function(item,event){
          if(!item.IsAllMarked){ 
        me.form.responseMessageVisible(false);
        me.selectedTest=item;
        me.formVisible(false);
      $.post("TestGenerationServlet",{action:"DeActivateTest",TestId:item.TestId},function(msg){
          
          if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                  /*
                  item.Activated="Yes"
                    var test={
                     TestId:me.selectedTest.TestId,
                     Name:me.selectedTest.Name,
                     TotalMark:me.selectedTest.TotalMark,
                     NumberOfQuestion:me.selectedTest.NumberOfQuestion,
                     StartDate:me.selectedTest.StateDate,
                     StartTime:me.selectedTest.StartTime,
                     EndTime:me.selectedTest.EndTime,
                     IsActivated:false,
                     Activated:"No"
                 } ;
                    test.Activated="No";
                    test.ActivatedStyle ="";
                    test.IsAllMarked=false;
                    test.MarkTestStyle ="";
                me.mytestList.replace(me.selectedTest,test);
                */
               me.onCourseChanged();
                me.form.responseDialog("Success!");
                me.form.responseMessageText("Test DeActivated");
                me.form.responseBoxStyle("alert alert-success");
                me.form.responseMessageVisible(true); 
                me.formVisible(false);
                }
                else{
                    me.form.responseDialog("Fail");
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-danger");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                me.form.responseDialog("DeActivate Test");
                me.form.responseMessageText("Can not deactivate a test already marked");
                me.form.responseBoxStyle("alert alert-info");
                me.form.responseMessageVisible(true); 
            }
          });
           }
           else{
               me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true);  
           }
       };
       
       me.onMarkTest=function(item,event){
           if(!item.IsAllMarked){
           me.selectedTest=item;
           $.post("TestGenerationServlet",{action:"MarkTest",TestId:item.TestId},function(msg){
                 if(msg != ""){
                      me.onCourseChanged(); //Refresh list
                me.form.responseDialog("Success!");
                me.form.responseMessageText("Test Marked");
                me.form.responseBoxStyle("alert alert-success");
                me.form.responseMessageVisible(true); 
                 }
                 else{
                me.form.responseDialog("Fail");
                me.form.responseMessageText(message.response.error);
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true);  
                 }
          });
         }
         else{
              me.form.responseDialog("Mark Test");
                me.form.responseMessageText("Can not re-mark test already maked");
                me.form.responseBoxStyle("alert alert-danger");
                me.form.responseMessageVisible(true);  
             event.preventDefault();
         }
       };
       
       me.form={
           
           heading:ko.observable("Create New"),
           name:ko.observable(""),
           questions:ko.observable(),
           marks:ko.observable(),
           startdate:ko.observable(""),
           startTime:ko.observable(""),
           endTime:ko.observable(""),
           isActivated:ko.observable(),
           responseMessageVisible:ko.observable(false),
           responseDialog:ko.observable("Error!"),
           responseMessageText:ko.observable(""),
           responseBoxStyle:ko.observable("alert alert-danger"),
           canShowUpdateButton:ko.observable(false),
           canShowSubmitButton:ko.observable(false),
           closeMessageBox:function(){
              me.form.responseMessageVisible(false) ;
           },fill:function(item){
               me.form.name(item.Name);
               me.form.questions(item.NumberOfQuestion);
               me.form.marks(item.TotalMark);
               me.form.startdate(item.StartDate);
               me.form.startTime(item.StartTime);
               me.form.endTime(item.EndTime);
               me.form.isActivated(item.IsActivated);
           },reset:function(){
               me.form.name("");
               me.form.questions("");
               me.form.marks("");
               me.form.startdate("");
               me.form.startTime("");
               me.form.endTime("");
               me.form.isActivated(false);
           }
             
       };
    
    
    // Validations
    
  var isDate = function(date) {
    return ((new Date(date)).toString() !== "Invalid Date") ? true : false;         
  };
  
 var  isvalidTime=function(inputStr) {
    if (!inputStr || inputStr.length<1) {return false;}
    var time = inputStr.split(':');
    return time.length === 2 
           && parseInt(time[0],10)>=0 
           && parseInt(time[0],10)<=23 
           && parseInt(time[1],10)>=0 
           && parseInt(time[1],10)<=59;
   };
  
 var   isNumeric=function(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
 };
 
 var  isValidTime=function(time) {
    var result = false, m;
    var re = /^\s*([01]?\d|2[0-3]):?([0-5]\d)\s*$/;
    if ((m = time.match(re))) {
        result = (m[1].length == 2 ? "" : "0") + m[1] + ":" + m[2];
    }
    return result;
};

//usage  07:00 to 15:00
//validate_time("08:00","07:00","15:00");
var  isValidtimeRange=function(t,st,et){
       t = t.split(/:/);
       st = st.split(/:/);
       et = et.split(/:/);
       return (t[0] < st[0] 
            || t[0] > et[0] 
            || (t[0] == st[0] && t[1] < st[1])
            || (t[0] == et[0] && t[1] > et[1]));
    };
  
   me.validate=function(){
      var hasError= false;
      var error="<ul>";
      //
      if(me.form.name()===""){
          hasError=true;
          error+="<li>Test Name is required</li>"
      }
      if(me.form.startdate()===""){
           hasError=true;
           error+="<li>Start Date is required:Format is MM/dd/YYYY</li>"
      }
      
      if(!isDate(me.form.startdate())){
          hasError=true;
           error+="<li>Invalid Start Date: :Format is MM/dd/YYYY</li>" 
      }
      
      if(me.form.startTime()===""){
           hasError=true;
            error+="<li>Start Time is required: format nn:mm</li>"
      }
      /*
      if(!isValidTime(me.form.startTime())){
           hasError=true;
           error+="<li>Invalid Start date: format nn:mm</li>"
      }
     */
  
        if(me.form.endTime()===""){
           hasError=true;
            error+="<li>End Time is required: format nn:mm</li>"
      }
      
      /* 
       if(!isValidTime(me.form.endTime())){
           hasError=true;
           error+="<li>Invalid End date: format nn:mm</li>"
      }
      */
       if(!isNumeric(me.form.marks())){
            hasError=true;
            error+="<li>Invalid marks</li>"
       };
      /*
          if(!isNumeric(me.form.questions())){
            hasError=true;
            error+="<li>Invalid number of questions</li>"
            
       };
       */
       return {
           hasError:hasError,
           error :error +"</ul>" 
       };
   };
     
};