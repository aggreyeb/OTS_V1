var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.BatchStudentAccounts=function(){
    var me= this;
    me.userList=ko.observableArray([]);
    
    me.selectedUser=null;
   me.UserType={
         
         Administrator:1,
         Student:2,
         Teacher:3
         
     };
   
    me.SelectedAction="";
  
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
   
  
    $(function(){
       me.HideFormHeading();
       me.SelectedUserType= me.UserType.Student;
      
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
      batchAccountVisible:ko.observable(false),
     
      closeMessageBox:function(event){
          me.form.responseMessageVisible(false);
      }
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
   
   me.onCancelAddNewUser=function(){
       me.form.formVisible(false);
       me.HideFormHeading();
       me.ClearError();
   };
   
    me.onDeleteUser=function(item,event){
     this.userList.remove(item);
   };
   
   me.onAddUser=function(){
       
        me.ClearError();
       me.student.Validate();
       if(!me.student.HasErrors){
        var user={
             Id:0,
             FirstName:me.student.FirstName(),
             LastName:me.student.LastName(),
             Phone:me.student.Phone(),
             Email:me.student.Email(),
             Password:"",
             UserTypeId:me.SelectedUserType
             };
       me.userList.push(user);
       me.student.Reset();
      }
      else{
          //display errors
          
        var errors=  this.student.BuildErrorMessage();
        this.DisplayError(errors);
      }
   };
 
   me.SubmitBatchUsers=function(){
    
       me.ClearError();
           var data=ko.toJSON(me.userList);
          
           $.post("UserManagementServlet",{action:"SaveBatchUsers", data:data},function(msg){
               try{ 
                var message =JSON.parse(msg);
                  if(message.response.status==="ok"){
                    me.form.formVisible(false);
                    me.form.headingVisisble(false);
                    me.form.responseDialog(me.form.dialogType.SUCCESS);
                    me.form.responseMessageText("Student accounts has been created. Temporay password is also created");
                    me.form.responseBoxStyle("alert alert-success");
                    me.form.responseMessageVisible(true); 
                    me.userList([]);
                    me.student.Reset();
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
        me.student.HasErrors=false;
   };
   
   me.SubmitBatchStudents=function(){
        alert("Submit batch");
   };
   
    me.onClear=function(){
      me.student.Reset();
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
};
