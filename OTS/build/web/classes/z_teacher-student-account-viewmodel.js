var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.StudentAccounts=function(){
    var me= this;
    me.studentList=ko.observableArray([]);
    me.selectedStudent=null;
    me.newStudent={Id:0,
                   FirstName:"Hello",
                   LastName:"",
                   Phone:"",
                   Email:"",
                   Password:"js"
                  };
    $(function(){
       me.HideFormHeading();
       me.studentList.push({Id:1,FirstName:"John",LastName:"Smith",Phone:"403-348-9089",Email:"js@testing.com",Password:"js"},
       {Id:2,FirstName:"Maggie",LastName:"Chang",Phone:"403-548-6089",Email:"jmc@testing.com",Password:"mc"})
    });
    
  
    me.form={ 
      dialogType:{CRITICAL:"Critical !",SUCCESS: "Success !", FAIL :"Fail !"},
      formVisible:ko.observable(true),
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
  
   me.OnCreateNew=function(){
       me.form.formVisible(true);
       me.form.assignStudentCourseVisible(false);
       me.form.batchAccountVisible(false);
       me.form.responseMessageVisible(false);
       me.form.createNewSudentVisible(true);
       me.form.headingText("Create New");
       me.ShowFormHeading();
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
          // ko.applyBindings(me,$("#view-content")[0]);
      });
   };
   
   me.CancelAddNewStudent=function(){
       me.form.formVisible(false);
       me.HideFormHeading();
   };
   
   me.SubmitNewStudent=function(){
       alert("Submit");
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
       me.selectedStudent=item;
       me.form.formVisible(true);
       me.form.assignStudentCourseVisible(false);
       me.form.batchAccountVisible(false);
       me.form.responseMessageVisible(false);
       me.form.createNewSudentVisible(true);
       me.form.headingText("Edit");
       me.ShowFormHeading();
   
   };
   
   me.onResetPassword=function(item,event){
        alert("Reset Student password");
   };
   
   me.onDeleteStudent=function(item,event){
        alert("Delete Student");
   };
   
    me.onStudentToCourse=function(item,event){
        alert("Student to Course");
   };
   
     me.onListSudentCourse=function(item,event){
        alert("List Student to Course");
   };
};
