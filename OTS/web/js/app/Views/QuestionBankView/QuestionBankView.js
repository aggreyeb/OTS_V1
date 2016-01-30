var OTS=OTS || {};
OTS.Views=OTS.Views || {};
OTS.Views.QustionBankView=function(otsAjax,questionBankViewModel){
    
    var ajax= otsAjax|| new OTS.Ajax();
    var viewModel=questionBankViewModel || new OTS.ViewModels.QuestionBankViewModel();
    var testQuestionUrl="";
    
 OTS.Views.QustionBankView.prototype.Render=function(){
     
 };  
 
 OTS.Views.QustionBankView.prototype.LoadRecords=function(){
     ajax.Success(function(msg){
         
     }).Complete(function(){
         
     }).Error(function(error){
         
     }).SendRequest("testQuestionUrl",{action:"",TestId:0},"Post");
 };
};
