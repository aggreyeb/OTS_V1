
var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.TestItemOption=function(id,name){
  var me=this;
  me.Id=id;
  me.Name=name;
  me.ItemTypes=[];
  me.NatureOfItemTypes=[];
 };
 
 OTS.Question=function(){
     var me = this;
     me.Number=0;
     me.QuestionUniqueId=0;
     me.QuestionText="";
     me.QuestionTypeId=0;
     me.QuestionType="";
     me.Selected=false;
     me.IsMultipleOptions=false;
     me.LineItems=[];
 };
 
 OTS.TestSheetItem=function(){
    var me= this;
    me.ItemNumber=0;
    me.ItemUniqueId=0;
    me.ItemText="";
    me.ItemTypeId=0;
    me.Type="";
    me.IsTrueFalse=false;
    me.IsMultipleChoiceMultipleAnswers=false;
    me.IsMultipleChoiceSingleAnswer=false;
    me.AnswerOptions=[];
 };
 
 OTS.AnswerOption=function(){
    var me=this;
    me.AnswerOptionId=""
    me.UniqueId=ko.observable("");
    me.AnswerOptionText="";
    me.AnswerOptionValue=ko.observable(false);
    me.LabelText="";
 };

OTS.TestItemOptions=function(){
    var items=[];
    var me=this;
    
    me.Seed=function(){
     //List
     
        var list= new OTS.TestItemOption(1,"List"); 
        list.ItemTypes.push({Id:1,Name:"TrueOrFalse"});
       // list.ItemTypes.push({Id:2,Name:"MultipleChoice-SingleAnswer"});
        list.ItemTypes.push({Id:3,Name:"MultipleChoice-MultipleAnwsers"});
        list.NatureOfItemTypes.push({Id:1,Name:"Correct"});
       // list.NatureOfItemTypes.push({Id:2,Name:"Negative"});
        //list.NatureOfItemTypes.push({Id:3,Name:"Incorrect"});
        //list.NatureOfItemTypes.push({Id:4,Name:"Negative-Incorrect"});
      
        items.push(list);
        
    // Describe   
        var describe=new OTS.TestItemOption(2,"Describe"); 
        describe.ItemTypes.push({Id:1,Name:"TrueOrFalse"});
        describe.ItemTypes.push({Id:2,Name:"MultipleChoice-SingleAnswer"});
        describe.ItemTypes.push({Id:3,Name:"MultipleChoice-MultipleAnwsers"});
        describe.NatureOfItemTypes.push({Id:1,Name:"Correct"});
        describe.NatureOfItemTypes.push({Id:2,Name:"Negative"});
        describe.NatureOfItemTypes.push({Id:3,Name:"Incorrect"});
        describe.NatureOfItemTypes.push({Id:4,Name:"Negative-Incorrect"});
       
        // items.push(describe);
     //Summarize
        var summarize=new OTS.TestItemOption(3,"Summarize"); 
        summarize.ItemTypes.push({Id:3,Name:"MultipleChoice-MultipleAnwsers"});
        summarize.NatureOfItemTypes.push({Id:1,Name:"Correct"});
           items.push(summarize);
        //Clasify
        var clasify=new OTS.TestItemOption(4,"Clasify"); 
        clasify.ItemTypes.push({Id:2,Name:"MultipleChoice-SingleAnswer"});
        clasify.ItemTypes.push({Id:3,Name:"MultipleChoice-MultipleAnwsers"});
        clasify.NatureOfItemTypes.push({Id:1,Name:"Correct"});
         items.push(clasify);
         return items;
    };
    me.Add=function(testItemOption){
        items.push(testItemOption)
    };
    
    me.Clear=function(){
        items.length=0;
    } ;
    
    me.Find=function(id){
       var item=null;
        for(var i=0;i<items.length;i++){
            if(items[i].Id===id){
                item=items[i];
                break;
            }
        }
        return item;
    };
 };

OTS.ViewModels.TestGenerationViewModel=function(){
   var me=this;
   var selectedCourse=null;
   var selectedTest=null;
   var selectedKnowledgeMap=null;
   var progressBar={};
   var testItemOptions= new  OTS.TestItemOptions().Seed();
   var msgBox= new OTS.MessageBox("div-myMessageBox");
   var ajax= new OTS.Ajax();
   
   var  conceptHierarchyInitialize=false;
   me.CognitiveTypeList=ko.observableArray([]);
   me.CognitiveTypeSelected=ko.observable();
   me.IsCognitiveListLoaded=false;
   
   me.QuestionTypeList=ko.observableArray([]);
   me.QuestionTypeSelected=ko.observable();
   
   me.QuestionNatureTypeList=ko.observableArray([]);
   me.QuestionNatureSelected=ko.observable();
   me.Message1=ko.observable("Hello World");
   me.EnableSubmit=ko.observable(true);
   me.EnableQuestionNature=ko.observable(true);
   
   me.CheckAllQuestionBank=ko.observable(false);
   me.CheckAllTestSheet=ko.observable(false);
  
  // Start teacher test management
   //Test Question Bank
  me.TestQuestionBank=ko.observableArray([]);
  me.TestSheetItems=ko.observableArray([]);
  me.AnswersSheetItems=ko.observableArray([]);
  me.TrueFalseClicked=function(e){
      alert(e);
  };
  
  me.CheckAllQuestionBankItems=function(){
     
    var state=  me.CheckAllQuestionBank();
     me.ToogleQuestionBankItemsSelection(state);  
  };
  
  me.CheckAllTestSheetItems=function(){
    var state=  me.CheckAllTestSheet();
     me.ToogleTestSheetItemsSelection(state);  
  };
  
  
  me.ToogleQuestionBankItemsSelection=function(state){
     var items= ko.toJS(me.TestQuestionBank());
     for(var i=0;i<items.length;i++){
          items[i].Selected=state
     }
     me.TestQuestionBank([]);
      for(var i=0;i<items.length;i++){
          me.TestQuestionBank.push(items[i]);
     }
  };
  
   me.ToogleTestSheetItemsSelection=function(state){
     var items= ko.toJS(me.TestSheetItems());
     for(var i=0;i<items.length;i++){
          items[i].Selected=state
     }
     me.TestSheetItems([]);
      for(var i=0;i<items.length;i++){
          me.TestSheetItems.push(items[i]);
     }
  };
  
  me.AddTestSheetItems=function(){
      var s =ko.toJSON(me.TestQuestionBank);
     // console.log(s);
      var qbItems =ko.toJS(me.TestQuestionBank);
      var selectedItems=[];
      for(var i =0;i<qbItems.length;i++){
         if (!qbItems[i].Selected) continue;
          selectedItems.push(qbItems[i]);
      }
      
      if(selectedItems.length>0){
      var data=JSON.stringify(selectedItems);
      $.post("TestQuestionBankServlet",{action:"AddTestSheetItems",CourseId:selectedCourse.Id,testid:selectedTest.TestId,itemJsons:data},function(msg){
          
           try{
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                   
                     me.LoadTestSheetItems();
                     me.LoadTestQuestionBank();
                     msgBox.DisplaySuccess("<p>Test sheet item(s) added </p>");
                      me.CheckAllQuestionBank(false);
                  }
                  else{
                      msgBox.DisplayError("<p>Failed to add items to test sheet </p>");
                  }
                }
                catch(error){
                   alert(error);
                }
      });
      }
      else{
           msgBox.DisplayError("<p>Select items to be added to test sheet </p>");
      }
  };
  
  me.UpdateTestSheet=function(){
     
      var qbItems =ko.toJS(me.TestSheetItems);
      for(var i=0;i<qbItems.length;i++)
      {
           for(var j=0;j<qbItems[i].AnswerOptions.length;j++){
              delete qbItems[i].AnswerOptions[j].Parent;
           }
      }
      var jdata=JSON.stringify(qbItems);
      $.post("TestQuestionBankServlet",{action:"UpdateTestSheet",testid:selectedTest.TestId, data:jdata},function(msg){
         
          try{
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                   //  alert("Pass");
                    me.LoadTestSheetItems();
                      msgBox.DisplaySuccess("<p>Test sheet item(s) Updated </p>");
                  }
                  else{
                     msgBox.DisplayError()("<p>Unable to update test sheet item(s) </p>");    
                  }
                }
                catch(error){
                   alert(error);
                }
     });
  };
  
  me.RemoveTestSheetItems=function(){
      var qbItems =ko.toJS(me.TestSheetItems);
      var items=[];
      for(var i=0;i<qbItems.length;i++)
      {
          if(qbItems[i].Selected){
           for(var j=0;j<qbItems[i].AnswerOptions.length;j++){
          
              delete qbItems[i].AnswerOptions[j].Parent;
           }
           items.push(qbItems[i]);
         }
      }
      if(items.length>0){
      var jdata=JSON.stringify(items);
     // $.post("TestQuestionBankServlet",{action:"RemoveTestSheetItems",CourseId:selectedCourse.Id,testid:selectedTest.TestId, data:jdata},function(msg){
          $.post("TestQuestionBankServlet",{action:"RemoveSelectedTestSheetItems",CourseId:selectedCourse.Id,testid:selectedTest.TestId, data:jdata},function(msg){
          try{
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                   //  alert("Pass");
                    me.LoadTestSheetItems();
                    me.LoadTestQuestionBank();
                     msgBox.DisplaySuccess("<p>Test sheet item(s) removed </p>");
                     me.CheckAllTestSheet(false);
                  }
                  else{
                     msgBox.DisplayError("<p>Unable to remove test items </p>"); 
                  }
                }
                catch(error){
                   alert(error);
                }
     });
    }
    else{
         msgBox.DisplayError("<p>Select item(s) to be removed and try again </p>"); 
    }
  };
  
  me.LoadTestSheetItems=function(){
          $.post("TestQuestionBankServlet",{action:"ListTestSheet",testid:selectedTest.TestId},function(msg){
               // console.log(msg);
                try{
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                    var contents=JSON.parse(message.response.content);
                    me.TestSheetItems([]);
                    for(var i=0;i<contents.length;i++){
                          contents[i].Mark=(contents[i].Mark).toFixed(2);
                          var item=ko.mapping.fromJS(contents[i]);
                        // me.TestSheetItems.push(contents[i]);
                         me.TestSheetItems.push(item);
                    }
                    
                    for(var j =0;j<me.TestSheetItems().length;j++){
                         
                         for(var x=0;x<me.TestSheetItems()[j].AnswerOptions().length;x++){
                             me.TestSheetItems()[j].AnswerOptions()[x].Parent=me.TestSheetItems;
                         }
                    }
                       var els= $(".input-mask");
                     $(".input-mask").mask("99.9");
                  }
                  else{
                      
                  }
                }
                catch(error){
                   alert(error);
                }
            });
  };
  //End teacher test management
  
  
    me.LoadTestAnswerSheetItems=function(){
          $.post("TestQuestionBankServlet",{action:"ListTestAnswerSheet",testid:selectedTest.TestId},function(msg){
               // console.log(msg);
                try{
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                    var contents=JSON.parse(message.response.content);
                    me.AnswersSheetItems([]);
                    for(var i=0;i<contents.length;i++){
                         contents[i].LineNumber=i+1;
                         if(contents[i].QuestionTypeId===1){
                            contents[i].QuestionTypeText="True/False"; 
                         }
                          if(contents[i].QuestionTypeId===2){
                            contents[i].QuestionTypeText="MultipleChoice-SingleAnswer"; 
                         }
                          if(contents[i].QuestionTypeId===3){
                            contents[i].QuestionTypeText="MultipleChoice-MultipleAnwsers"; 
                         }
                         
                       // var item=ko.mapping.fromJS(contents[i]);
                        
                         me.AnswersSheetItems.push(contents[i]);
                    } 
                  }
                  else{
                      
                  }
                }
                catch(error){
                   alert(error);
                }
            });
  };
  
  me.LoadTestQuestionBank=function(){
      console.log(selectedTest.TestId);
            $.post("TestQuestionBankServlet",{action:"ListQuestionBank",testid:selectedTest.TestId,CourseId:selectedCourse.Id},function(msg){
               // console.log(msg);
                try{
                 var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                    var contents=JSON.parse(message.response.content);
                    me.TestQuestionBank([]);
                    for(var i=0;i<contents.length;i++){
                         me.TestQuestionBank.push(contents[i]);
                    }
                  }
                  else{
                      
                  }
                }
                catch(error){
                   alert(error);
                }
            });
  };
  
  me.TabChanged=function(item,e){
      var item=e;
      //console.log(e.currentTarget.innerText);
       msgBox.DisplayInformation("<p></p>"); 
      switch(e.currentTarget.innerText){
          case "Generate Test Items":
              
              break;
          case "Test Question Bank":
           
              me.LoadTestQuestionBank();
           
            break;
            
           case "Test Sheet":
              me.LoadTestSheetItems();
             
     
            break;
           
          case "Answer Sheet":
            me.LoadTestAnswerSheetItems();
              break;
            
          default:
              break;
            
      }
  };
  
  
   
   me.CognitiveTypeChanged=function(){
       $("#div-outputheader").html("<p></p>");
         $("#div-output-body").empty();
     var item=null;
      var items=testItemOptions;
      for(var i=0;i<items.length;i++){
          if(items[i].Id===me.CognitiveTypeSelected()){
              item=items[i];
              break;
          }
      }
      me.QuestionTypeList([]);
      if(item!==null &&  item.ItemTypes!==null){
      for(var i=0;i<item.ItemTypes.length;i++){
         if(item !==undefined && item.Name==="Clasify"){
             if(item.ItemTypes[i].Id!==3){
                 me.QuestionTypeList.push(item.ItemTypes[i]);
             }
         }
         else{
              me.QuestionTypeList.push(item.ItemTypes[i]); 
         }
        }
     }
       me.QuestionNatureTypeList([]);
       if(item!==null &&  item.NatureOfItemTypes!==null){
       for(var i=0;i<item.NatureOfItemTypes.length;i++){
          me.QuestionNatureTypeList.push(item.NatureOfItemTypes[i]);
      }
     }
      //if congnitive type is Describe and selected node is parent
      me.CheckParentDescribeCognitiveSelected($("#cboCognitive").val(),selectedKnowledgeMap)
     
   };
   
   me.CheckParentDescribeCognitiveSelected=function(cognitiveType,node){
         if(cognitiveType===undefined || node===undefined || node===null)
             return;
       if(cognitiveType==="2" & node.parentid===undefined ){
        
          me.EnableSubmit(false);
          me.CognitiveTypeSelected('');
          me.QuestionTypeSelected('');
          me.QuestionNatureSelected('');
          $("#div-messageBox").hide();
          $("#div-outputheader").empty();
       
          $("#div-output-body").empty();
          $("#div-output-body").html("<p>Describe is not applicable for root node</p>");
         
      }
      else{
            me.EnableSubmit(true);
            $("#div-output-body").empty();
      }
   };
   
    me.QuestionTypeChanged=function(currentItem){
      
       $("#div-outputheader").html("<p></p>");
       $("#div-output-body").empty();
       var item=null;
      var items=testItemOptions;
      for(var i=0;i<items.length;i++){
          if(items[i].Id===me.CognitiveTypeSelected()){
              item=items[i];
              break;
          }
      }
      
    
       var foundItemType=null;
       if(item!==null && item.ItemTypes!==null){
      for(var i=0;i<item.ItemTypes.length;i++){
         if(item.ItemTypes[i].Id===me.QuestionTypeSelected()){
             foundItemType=item.ItemTypes[i];
             break;
         }
      }
      }
     if(foundItemType!==null){
     if(foundItemType.Id===2 || foundItemType.Id===3){
         me.EnableQuestionNature(false);
         me.QuestionNatureSelected(1);
     }
     else{
          me.EnableQuestionNature(true);
     }
    }
   };
   
   
  
   me.CourseTestKnowledgeMapsList=ko.observableArray([]);
   me.KnowledgeMapSelected={
       NodeName:ko.observable("") ,
       ParentNode:ko.observable(""),
       RelationWithParent:ko.observable(""),
       RelationType:ko.observable(""),
       IncludeSubTrees:ko.observable(true),
        update:function(node){
         me.KnowledgeMapSelected.NodeName(node.name);
         me.KnowledgeMapSelected.ParentNode(node.parent.name); 
         me.KnowledgeMapSelected.RelationWithParent(node.relationtype);
       }
     
   };
  
     $(function(){
     msgBox.DisplayInformation("<p></p>"); 
          $('#tv-courseknowledgemaps').tree({
               data:  me.data, autoOpen: true
          });
          $("#chk-all-testsheet").change(function(){
              alert("Hi");
          });
     });
     
      me.updateTreeView=function(items){
            
               if(!conceptHierarchyInitialize){
               $('#tv-courseknowledgemaps').tree({
                             data: items, autoOpen: true
               });
                   conceptHierarchyInitialize=true;
                 }
                 else{
                       $('#tv-courseknowledgemaps').tree('loadData', items);  
                   }      
       };
     
     me.changeContentHeading=function(name){
       $("#lbl-selected-menuitem").text(name);
      }; 
    
    me.loadView=function(item,course){
         me.changeContentHeading("Tests - Course: " + course.Name + "       " + "          [Test:" + item.Name + "]");
         selectedCourse=course;
         selectedTest=item;
         if(item.IsActivated){
           me.EnableSubmit(false); 
         }
      
          //teacher-tests_generation.html
          $.get("templates/teacher/teacher-test-management.html",function(msg){
           $("#view-content").empty();
           $("#view-content").append(msg);
           
           $("#btn-submit").attr("disabled",'disabled');
           
             ko.applyBindings(me,$("#view-content")[0]);
          
           
             $.post("LookupListServlet",{action:"ListCourseTestKnowledgeMaps",CourseId:selectedCourse.Id},function(msg){
                 // alert(msg);
                   try{
                  var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                    var data=[];
                     var contents=JSON.parse(message.response.content);
                     for(var i=0;i<contents.length;i++){
                           var item=JSON.parse(contents[i]);
                           var knowledgeMap={};
                            knowledgeMap.id=item.id;
                            knowledgeMap.label=item.label;
                            knowledgeMap.identity=item.identity;
                            knowledgeMap.description=item.description;
                            knowledgeMap.parentid=item.parentid;
                            knowledgeMap.rootid=item.rootid;
                            knowledgeMap.children=item.children;
                       data.push(knowledgeMap);
                     
                    }
                     me.updateTreeView(data);
                     me.LoadCognitiveList();
                 
                    // me.LoadQuestionTypeList();
                    //  me.LoadQuestionNatureList();
                      progressBar=$("#spProgress");
                      $("#spProgress").hide();
                  }
                  else{
                   
                     alert(message.response.error);
                  }
                 }catch(ex){
                    alert(ex);
                  }
            });
           
            $('#tv-courseknowledgemaps').bind(
           'tree.click',
            function(event) {
             var node = event.node;
              //alert(node.parent.name);
              selectedKnowledgeMap=node;
               me.KnowledgeMapSelected.update(node);
               me.CheckParentDescribeCognitiveSelected($("#cboCognitive").val(),selectedKnowledgeMap);
               $("#div-outputheader").empty();
             }
           );
         
   
       });
    };
    me.LoadCognitiveList=function(){
         $.post("LookupListServlet",{action:"ListCognitiveTypes"},function(msg){
            var message =JSON.parse(msg);
            var status=message.response.status;
           if(status==="ok"){
                     var contents=JSON.parse(message.response.content);
                     for(var i=0;i<contents.length;i++){
                      var item=contents[i];
                       me.CognitiveTypeList.push(item);
                    }
                }
             
           });  
   };   
    
   me.LoadQuestionTypeList=function(){
         $.post("LookupListServlet",{action:"ListQuestiontTypes"},function(msg){
            var message =JSON.parse(msg);
            var status=message.response.status;
           if(status==="ok"){
                     var contents=JSON.parse(message.response.content);
                     for(var i=0;i<contents.length;i++){
                      var item=contents[i];
                       me.QuestionTypeList.push(item);
                    }
                }
             
           });  
   };
   
   me.LoadQuestionNatureList=function(){
     $.post("LookupListServlet",{action:"ListQuestionnatureTypes"},function(msg){
            var message =JSON.parse(msg);
            var status=message.response.status;
           if(status==="ok"){
                     var contents=JSON.parse(message.response.content);
                     for(var i=0;i<contents.length;i++){
                      var item=contents[i];
                       me.QuestionNatureTypeList.push(item);
                    }
                }
           });  
   };
   
   me.CanSubmit=ko.computed(function(){
      
       var result= me.CognitiveTypeSelected()!==undefined & 
               me.QuestionTypeSelected() !==undefined &
               me.QuestionNatureSelected() !==undefined & selectedKnowledgeMap!==null
      return result;
    
   });
   
   me.Validate=function(){
       
       var error="";
       var hasError=false;
      
       if(me.CognitiveTypeSelected()===undefined){
           hasError=true;
           error+="<li> CognitiveType required</li>";
           
       }
       // var x=   $("#cboQuestionType").val();
       //  alert(x);
       if($("#cboQuestionType").val()===undefined || $("#cboQuestionType").val()===""){
           hasError=true;
           error+="<li> Item Type required</li>";
           
       }
       
    // var y=$("#cboQuestionNature").val();
    // alert(y);
        if($("#cboQuestionNature").val()===undefined || $("#cboQuestionNature").val()===""){
           hasError=true;
           error+="<li> Nature of Item required</li>";
           
       }
       
        if(selectedKnowledgeMap===null){
           hasError=true;
           error+="<li>Select knowledge map node</li>";
           
       }
       
       return {
           hasError:hasError,
           error:error
           
       };
   };
   
   
   me.Submit=function(){
    
  
     $("#div-messageBox").hide();
    var result= me.Validate();
    if(result.hasError){
      
        $("#div-messageBox").empty();
        $("#div-messageBox").html(result.error);
        $("#div-messageBox").show();
    }
    else{
     $("#spProgress").text("Please wait generating test item ...");
     $("#spProgress").show();
     var course= selectedCourse;
     var test=  selectedTest; 
     var knowledgeMap=selectedKnowledgeMap;
      var knowledgeMapId=0;
      var nodeIdentity="";
      var nodeParent=0;
      if(knowledgeMap.rootid===undefined){
          knowledgeMapId=knowledgeMap.id;
      }
      else{
          knowledgeMapId=knowledgeMap.rootid;
          nodeIdentity=knowledgeMap.identity;
          nodeParent=knowledgeMapId;
      }
       var input={
           KnowledgeMapId:knowledgeMapId,
           NodeParent:nodeParent,
           NodeIdentity:nodeIdentity,
           CourseId:course.Id,
           TestId:test.TestId,
           IncludeSubTrees: me.KnowledgeMapSelected.IncludeSubTrees(),
           CognitiveType:$("#cboCognitive").val(),
           ItemType:$("#cboQuestionType").val(),
           NatureOfItem:$("#cboQuestionNature").val(),
          // UniqueId:$("#cboAlgorithmType").val()
           UniqueId:$("#cboCognitive option:selected").text().trim() + 
                   $("#cboQuestionType option:selected").text().trim() +
                   $("#cboQuestionNature option:selected").text().trim()
       };
          $("#div-outputheader").html("<p></p>");
       $.post("TestGenerationServlet",{action:"GenerateTestItem", data:JSON.stringify(input)},function(msg){
        
           try{
            var message =JSON.parse(msg);
            
             if(message.response.status==="ok"){
                  var content=JSON.parse(message.response.content);  
                   me.displayGeneratedItem(content);
                }
                else{
                  
                     //alert("Error occurs will generating test item") ;
                     var content=JSON.parse(message.response.content); 
                    // alert(message.response.error);
                     $("#div-outputheader").html("<br><b>" + content.Error + "</b>");
                }
          
           }
           catch(error){
               
              alert(error) ;
           }
           $("#spProgress").hide();
       });
   }
   };
   
   me.displayGeneratedItem=function(contents){
      // console.log(contents);
      var text="";
      for(var i=0;i<contents.length;i++){
          
          for(j=0;j<contents[i].LineItems.length;j++){
           
             text+="<p>" +  contents[i].LineItems[j] +"</p>"
          }
           
      }
     // $("#div-outputheader").empty();
   
   
     if($("#cboQuestionType").val()==="1"){
        $("#div-outputheader").html("<br><b>" + "True or False "+ "</b>");
     }
     else{
       $("#div-outputheader").html("<br><b>" + contents[0].Text + "</b>");
     }
    
      $("#div-output-body").empty();
      $("#div-output-body").html(text);
      if(text.trim().length===0){
           $("#div-outputheader").html("<p></p>");
    }
   };
   
   
   
};

