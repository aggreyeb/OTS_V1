var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};

OTS.ViewModels.ReviewTestLineItemsViewModel=function(){
    var me=this;
     me.mytestQuestionLineItems=ko.observable([{Text:"Hello world"}]);
};

OTS.ViewModels.ReviewTestQuestionsViewModel=function(){
    var me= this;
     var selectedCourse=null;
     var selectedTest=null;
     me.testQuestions=ko.observableArray([]);
     me.testQuestionLineItems=ko.observableArray([]);
     me.selectedTestItem=ko.observable("");
   
      me.loadView=function(item,course){
         me.changeContentHeading("Tests - Course: " + course.Name + "       " + "          [Test:" + item.Name + "]");
         selectedCourse=course;
         selectedTest=item;
          me.LoadRecords(selectedCourse,selectedTest);
      };
      
       me.changeContentHeading=function(name){
       $("#lbl-selected-menuitem").text(name);
      };
      
      me.LoadRecords=function( selectedCourse,selectedTest){
          
          $.post("TestGenerationServlet",{action:"ListTestQuestions", TestId:selectedTest.TestId,CourseId:selectedCourse.Id},function(msg){
             
               var message =JSON.parse(msg);
               var status=message.response.status;
                if(status==="ok"){
                   var contents=JSON.parse(message.response.content);
                     for(var i=0;i<contents.length;i++){
                           var item=contents[i];
                           var testQuestion={};
                            testQuestion.Row=i +1;
                            testQuestion.QuestionId=item.QuestionId;
                            testQuestion.TestId=item.TestId;
                            testQuestion.Text=item.Text;
                            testQuestion.CognitiveType=item.CognitiveType;
                            testQuestion.QuestionType=item.QuestionType;
                            testQuestion.QuestionNatureType=item.QuestionNatureType;
                            if(item.Correct===undefined){
                              testQuestion.Correct=false;
                            }
                            else{
                             testQuestion.Correct=item.Correct;   
                            }
                          me.testQuestions.push(testQuestion);
                          
                    }
                      //me.testQuestionLineItems.push([]);
                        $.get("templates/teacher/teacher-review-test-questions.html",function(msg){
                        $("#view-content").empty();
                        $("#view-content").append(msg);
                         ko.applyBindings(me,$("#view-content")[0]);
                        });
                }
                else{
                  alert("Fail to load records");  
                }
           });
         
      };
      
   
    
      me.onEdit=function(item,event){
        loadLineItems(item);
      };
    
    var loadLineItems=function(item){
        
       me.selectedTestItem(item.Row);
        $.post("TestGenerationServlet",{action:"ListTestQuestionLineItems",QuestionId:item.QuestionId},function(msg){
           console.log(msg);   
           var message =JSON.parse(msg);
           var status=message.response.status
           if(status==="ok"){
                  var contents=JSON.parse(message.response.content);
                     for(var i=0;i<contents.length;i++){
                        var item=contents[i];
                        var l={};
                         l.QuestionLineItemId=item.QuestionLineItemId;
                         l.Text=item.Text;
                         l.Question_id=item.Question_id;
                         l.IsCorrect=item.IsCorrect;
                         me.testQuestionLineItems.push(l);
                     }
           }
           else{
               //error
           }
           
        });
       
    };
    
    
     me.onDelete=function(item,event){
        console.log( ko.toJSON(me.testQuestionLineItems));
       var json=  ko.toJSON(me.testQuestionLineItems);
         alert(json);
      };
      
      me.onSubmitQuestionLineItems=function(){
           $("#div-message").hide();
          var json=  ko.toJSON(me.testQuestionLineItems);
           $.post("TestGenerationServlet",{action:"UpdateQuestionLineItemBatch",QuestionLineItems:json},function(msg){
               
             var message =JSON.parse(msg);
             var status=message.response.status
               
           if(status==="ok"){
                  //alert("Pass");
               $("#div-message").show();
                $("#p-message").text("Success ! Update done successfully");
           }
           else{
               //alert(JSON.stringify(msg));
               // $("#p-message").text(message.response.error);
                $("#p-message").text("Error! Unable to update Item(s)" +  message.response.error );
                $("#div-message").removeClass("alert-success");
                $("#div-message").addClass("alert-danger");
                $("#div-message").show();
           }
           });
      };
};


