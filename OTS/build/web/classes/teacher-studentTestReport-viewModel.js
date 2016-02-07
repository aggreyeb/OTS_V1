var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.StudentTestReport= function(){
    var me=this;
    me.TestList=ko.observableArray([]);
    me.SelectedTest=ko.observable();
    me.StudentTestList=ko.observableArray([]);
    
   
    
    me.LoadTestLookup=function(){
         $.post("TestQuestionBankServlet",{action:"ListTeacherTestLookup"},function(msg){
        
           me.TestList([]);
          var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
            
                 me.TestList.push(contents[i]);
            }
       });
    };
    
    me.LoadStudentTestList=function(){
        $.post("TestQuestionBankServlet",{action:"ListStudentTestReport",testid:me.SelectedTest()},function(msg){
         if(msg!=""){
           me.StudentTestList([]);
          var message =JSON.parse(msg);
          var contents=JSON.parse(message.response.content);
           for(var i=0;i<contents.length;i++){
                 if(contents[i].TotalMark<0){
                     contents[i].Marked="No";
                     contents[i].TotalMark="InProgress"
                 }
                 else{
                      contents[i].Marked="Yes";
                 }
                 me.StudentTestList.push(contents[i]);
            }
            console.log(me.StudentTestList);
        }
       });
    };
    
    me.onTestChanged=function(){
       if(me.SelectedTest()==undefined ||me.SelectedTest()==null ) return;
        me.LoadStudentTestList();
       
    };
    
    $(function(){
        me.LoadTestLookup();
    })
};
