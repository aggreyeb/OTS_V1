var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.StudentMyTestViewModel=function(){
    var me=this;
    var selectedTest=null;
    var msgBox= new OTS.MessageBox("div-myMessageBox");
    me.TestName=ko.observable("");
    me.StartDate=ko.observable("");
    me.StartTime=ko.observable("");
    me.EndTime=ko.observable("");
    
    me.TestList=ko.observableArray([]);
    me.TestSheetItems=ko.observableArray([]);
    me.EnableSubmitTest=ko.observable(true);
    
    
    me.TakeTest=function(item,event){
      selectedTest=item;
      me.TestName(item.TestName);
      me.StartDate(item.StartDate);
      me.StartTime(item.StartTime);
      me.EndTime(item.EndTime);
       try{
         $.post("TestQuestionBankServlet",{action:"RecordStudentTestStartDateTime",testid:selectedTest.TestId},function(msg){
            
          });
      }
      catch(error){
          alert(error);
      }
      me.loadView(item);
      // Log  the start date Time;
     
     
    };
    
    
    me.SubmitTestSheet=function(){
     
      var qbItems =ko.toJS(me.TestSheetItems);
      for(var i=0;i<qbItems.length;i++)
      {
           for(var j=0;j<qbItems[i].AnswerOptions.length;j++){
              delete qbItems[i].AnswerOptions[j].Parent;
           }
      }
      var jdata=JSON.stringify(qbItems);
      $.post("TestQuestionBankServlet",{action:"SubmitStudentTestSheet",testid:selectedTest.TestId, data:jdata},function(msg){
         
          try{
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                 
                    me.LoadTestSheetItems();
                      msgBox.DisplaySuccess("<p>Your test has been submitted </p>");
                      me.EnableSubmitTest(false);
                  }
                  else{
                    msgBox.DisplayError("<p>Unable to submit your test. Contact your professor </p>");  
                    
                  }
                }
                catch(error){
                   alert(error);
                }
     });
  };
    
    
    me.ResetTestAnswers=function(question){
         var answers=question.AnswerOptions;
        for(var i=0;i<answers.length;i++){
            answers[i].IsCorrect=false;
            answers[i].IsChecked='false';
            answers[i].CheckedStyle='';
        }
        return question;
    };
    
     me.LoadTestSheetItems=function(testId){
          $.post("TestQuestionBankServlet",{action:"ListTestSheet",testid:testId},function(msg){
               // console.log(msg);
                try{
                 if(msg!==""){
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                    var contents=JSON.parse(message.response.content);
                    me.TestSheetItems([]);
                    for(var i=0;i<contents.length;i++){
                        var question=me.ResetTestAnswers(contents[i]);
                         var item=ko.mapping.fromJS(question);
                         me.TestSheetItems.push(item);
                    }
                    
                    for(var j =0;j<me.TestSheetItems().length;j++){
                         
                         for(var x=0;x<me.TestSheetItems()[j].AnswerOptions().length;x++){
                             me.TestSheetItems()[j].AnswerOptions()[x].Parent=me.TestSheetItems;
                         }
                    }
                    
                  }
                  else{
                      
                  }
                }
               }
                catch(error){
                   alert(error);
                }
            });
     };
     
     
     me.LoadTestList=function(){
       
        $.post("CourseServlet",{action:"ListSudentActivatedTest"},function(msg){
            try{
               if(msg!==""){
               var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               me.TestList([]);
               if(contents.length>0){
               for(var i=0;i<contents.length;i++){
                 if(contents[i].IsTestTeken){
                     contents[i].CanEnableTest=false;
                     contents[i].CanTestInformationEnable=true;
                     contents[i].TestTaken="Yes";
                 }
                 else{
                    contents[i].CanEnableTest=true; 
                    contents[i].CanTestInformationEnable=false;
                    contents[i].TestTaken="No";
                 }
                 me.TestList.push(contents[i]);
                }  
               }
              }
            }catch(ex){
                
                alert(ex);
            }
        });
    };
    
     $(function(){
        
       me.LoadTestList();
    });
    
    
     me.loadView=function(item){
           me.LoadTestSheetItems(item.TestId);
          console.log(item);
          $.get("templates/student/student-taketest.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
          ko.applyBindings(me,$("#view-content")[0]);
       
       });
    };
    
};

