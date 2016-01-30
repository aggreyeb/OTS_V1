var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.StudentAccounts=function(courseAssignmentViewModel){
    var me= this;
  
     //ViewModels
    var  caViewModel= courseAssignmentViewModel||new OTS.ViewModels.TeacherCourseAssigment();
    
    me.userList=ko.observableArray([]);
    me.userTypeList=ko.observableArray([
        {Id:1,Name:"Administrator"},
        {Id:2,Name:"Student"},
        {Id:3,Name:"Teacher"},  
    ]);
    me.userDropDownSelected=ko.observable();
    me.IsTeacherSelected=ko.observable(false);
    me.selectedUser=null;
    me.UserType={
         
         Administrator:1,
         Student:2,
         Teacher:3
         
     };
     
    me.ActionType={
        NEW:"new",
        UPDATE:"update",
        DELETE:"delete" ,
        ResetPassWord:"ResetPassword",
        UNKNOW:""
    };
    me.SelectedAction="";
    
    me.SelectedUserType=me.UserType.Student;
    
    me.student={Id:ko.observable(0),
                   FirstName:ko.observable(""),
                   LastName:ko.observable(""),
                   Phone:ko.observable(""),
                   Email:ko.observable(""),
                   Password:ko.observable(""),
                   Message:[],
                   HasErrors:false,
                   ClearMessage:function(){
                       me.student.Message=[];
                   },
                   Update:function(item){
                       me.student.FirstName(item.FirstName);
                       me.student.LastName(item.LastName);
                       me.student.Phone(item.Phone);
                       me.student.Email(item.Email);
                       me.student.Password(item.Password);
                   },
                   Reset:function(){
                       me.student.FirstName("");
                       me.student.LastName("");
                       me.student.Phone("");
                       me.student.Email("");
                       me.student.Password("");
                   },
                   HasValidEmail:function(){
                      if(me.student.Email()!==""){
                           return true;
                      }
                      else{
                          return false; 
                      }
                     
                   },
                   HasValidPhoneNumber:function(){
                       if( me.student.Phone()!==""){
                          return true;
                       }
                       else{
                           return false;
                       }
                   },
                   Validate:function(){
                      
                       if(!me.student.HasValidEmail() ){
                          me.student.Message.push("Invalid Email");
                         me.student.HasErrors=true;
                       }
                               
                      if(!me.student.HasValidPhoneNumber()){
                          me.student.HasErrors=true;
                          me.student.Message.push("Invalid PhoneNumber");
                      }
                      
                      if(me.student.FirstName()===""){
                          me.student.HasErrors=true;
                          me.student.Message.push("FirstName required");
                      }
                      
                     if(me.student.LastName()===""){
                          me.student.HasErrors=true;
                          me.student.Message.push("LastName required");
                      }
                     
                   },
                   BuildErrorMessage:function(){
                       var html="<ul>";
                        for(var i=0;i<me.student.Message.length;i++){
                            html+="<li>" + me.student.Message[i] + "</li>";
                        };
                     html+="</ul>";
                     return html;
                   }
                  };
   
    
    me.loadUsers=function(){
         me.userList([]);
         if(me.SelectedUserType===me.UserType.Teacher){
             me.IsTeacherSelected(true);
         }
         
         $.post("UserManagementServlet",{action:"ListUsers",userTypeId: me.SelectedUserType},function(msg){
           try{
               var message =JSON.parse(msg);
                  if(message.response.status==="ok"){
                   
                     var users=JSON.parse(message.response.content);
                     for(var i=0;i<users.length;i++){
                           users[i].IsTeacherSelected=me.IsTeacherSelected();
                          if(users[i].Email===undefined){
                            users[i].Email="" ;
                         }
                          if(users[i].Phone===undefined){
                            users[i].Phone="" ;
                         }
                         me.userList.push(users[i]);
                     }
                      
                  }else{
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
       });
       
    };
    
    
    
    $(function(){
       me.HideFormHeading();
       me.SelectedUserType= me.UserType.Student;
       me.loadUsers();
    });
    
  
    me.form={ 
      dialogType:{CRITICAL:"Critical !",SUCCESS: "Success !", FAIL :"Fail !"},
      formVisible:ko.observable(false),
      createNewSudentVisible:ko.observable(false),
      assignStudentCourseVisible:ko.observable(false),
      batchAccountVisible:ko.observable(false),
      listStudentCourseVisible:ko.observable(false),
      responseMessageVisible:ko.observable(false),
      responseDialog:ko.observable("Mock Page!"),
      responseMessageText:ko.observable("This is a mock page for review.Function not implemented"),
      responseBoxStyle:ko.observable("alert alert-info"),
      headingText:ko.observable(""),
      headingVisisble:ko.observable(false),
      batchAccountVisible:ko.observable(false),
      assignStudentCourse:ko.observable(false),
      listStudentCourse:ko.observable(false),
      closeMessageBox:function(event){
          me.form.responseMessageVisible(false);
      }
    };
  
   me.UserTypeChanged=function(){
      
          var selected=  me.userDropDownSelected();
          if(selected===1){
              me.SelectedUserType=me.UserType.Administrator;
          }
          if(selected===2){
              me.SelectedUserType=me.UserType.Student;
          }
          
           if(selected===3){
              me.SelectedUserType=me.UserType.Teacher;
          }
          me.loadUsers();
          me.form.responseMessageVisible(false);
   
   };
  
 

  
  me.SelectUser=function(userType){
      $("#sel-user").text(userType);
  };
  
   me.OnCreateNew=function(){
       me.SelectedAction=me.ActionType.NEW;
       me.form.formVisible(true);
       me.form.assignStudentCourseVisible(false);
       me.form.batchAccountVisible(false);
       me.form.responseMessageVisible(false);
       me.form.createNewSudentVisible(true);
       me.form.headingText("Create New");
       me.ShowFormHeading();
       me.student.Reset();
       me.student.ClearMessage();
   };
   
   me.ShowFormHeading=function(){
       $("#div-form-heading").show();
   };
   
   me.HideFormHeading=function(){
       $("#div-form-heading").hide();
   };
   me.OnCreateBatch=function(){
       $.get("templates/teacher/teacher-studentaccounts-batch.html",{},function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
           $("#lbl-selected-menuitem").text("Student Accounts-Batch");
      });
   };
   
   me.CancelAddNewStudent=function(){
       me.form.formVisible(false);
       me.HideFormHeading();
       me.ClearError();
   };
   
    me.onDeleteStudent=function(item,event){
        me.SelectedAction=me.ActionType.DELETE;
        me.form.formVisible(false);
        me.form.headingText("Delete");
        me.selectedUser=item;
        me.form.responseMessageVisible(false) ;
        var currentItem={
               value:me.selectedUser
           };
        $.post("UserManagementServlet",{action:"DeleteUser",Id:me.selectedUser.Id},function(msg){
             try{ 
                var message =JSON.parse(msg);
                
                  if(message.response.status==="ok"){
                     
                    me.userList.remove(currentItem.value);
                    me.form.formVisible(false);
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
               
        });
        me.selectedUser=null;
   };
   
   me.SubmitUser=function(){
      me.SubmitNewStudent();
   };
   
   
   me.SubmitNewStudent=function(){
   
       me.ClearError();
       me.student.Validate();
       if(!me.student.HasErrors){
        var userId=0;
        if(me.selectedUser!==null){
            userId=me.selectedUser.Id;
        }
        var user={
             Id:userId,
             FirstName:me.student.FirstName(),
             LastName:me.student.LastName(),
             Phone:me.student.Phone(),
             Email:me.student.Email(),
             Password:"",
             UserTypeId:me.SelectedUserType
             };
           var currentItem={
               value:me.selectedUser
           };
           $.post("UserManagementServlet",{action:"SaveUser", data:JSON.stringify(user)},function(msg){
             
               var selectedUser=currentItem.value;
               try{ 
                var message =JSON.parse(msg);
                
                  if(message.response.status==="ok"){
                     
                     var item=JSON.parse(message.response.content);
                     
                     if(me.SelectedAction==="new"){
                       user.Id=message.response.id;
                       user.Password=item.Password;
                       me.userList.push(user);
                    }
                    else{
                        me.userList.replace(selectedUser,user);
                    }
                    me.form.formVisible(false);
                    me.form.headingVisisble(false);
                    me.form.responseDialog(me.form.dialogType.SUCCESS);
                    me.form.responseMessageText("Operation done Successfully");
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
               
           });
      
          } 
          else{
          var error=  me.student.BuildErrorMessage();
            me.DisplayError(error);
          }
          me.selectedUser=null;
      };     
        
   
   me.DisplayError=function(html){
       $("#div-validation").show();
       $("#div-validation").html(html);
   };
   
    me.ClearError=function(){
       me.student.ClearMessage();
       $("#div-validation").hide();
       $("#div-validation").empty();
        me.form.responseMessageVisible(false) ;
       
   };
   
   me.SubmitBatchStudents=function(){
        alert("Submit batch");
   };
   
    me.CancelBatchStudents=function(){
        me.form.batchAccountVisible(false);
        me.HideFormHeading();
   };
   
   //List Actions
   me.onEditStudent=function(item,event){
       me.selectedUser=item;
       me.SelectedAction=me.ActionType.UPDATE;
       me.form.formVisible(true);
       me.form.headingVisisble(true);
       me.form.assignStudentCourseVisible(false);
       me.form.batchAccountVisible(false);
       me.form.responseMessageVisible(false);
       me.form.createNewSudentVisible(true);
       me.form.headingText("Edit");
       me.ShowFormHeading();
       me.student.Update(item);
       me.ClearError();
   };
   
   me.onResetPassword=function(item,event){
       me.SelectedAction=me.ActionType.ResetPassWord;
       
      $.post("UserManagementServlet",{action:"ResetPassword",userId:item.Id},function(msg){
             try{ 
                var message =JSON.parse(msg);
                
                  if(message.response.status==="ok"){
                  
                    me.form.formVisible(false);
                    me.form.headingVisisble(false);
                    me.form.responseDialog(me.form.dialogType.SUCCESS);
                    me.form.responseMessageText("Password reset done successfully");
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
               
        });
        me.selectedUser=null;
   };
   
  
    me.onStudentToCourse=function(item,event){
        alert("Student to Course");
   };
   
     me.onListSudentCourse=function(item,event){
        alert("List Student to Course");
   };
   
   me.onTeacherCourseAssignment=function(item,event){
     var teacherCourseList=[];
     var  courses=[];
      $.post("CourseServlet",{action:"ListCoursesByTeacher", TeacherId:item.Id},function(msg){
          var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
            
                teacherCourseList.push(contents[i]);
            }
          }).complete(function(){
           "CourseServlet",{action:"ListCoursesByTeacher", TeacherId:item.Id}
              $.post("CourseServlet",{action:"ListTeacherUnAssignedCourses", TeacherId:item.Id},function(msg){
                  
                   var message =JSON.parse(msg);
                   var contents=JSON.parse(message.response.content);
                    for(var i=0;i<contents.length;i++){
                      courses.push(contents[i]);
                   }
                 
                  
              }).complete(function(){
                caViewModel.LoadView(item,teacherCourseList,courses);  
              });
               
          });  
     
   };
};
