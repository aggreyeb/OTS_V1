var OTS=OTS || {};
OTS.ViewModels=OTS.ViewModels || {};
OTS.ViewModels.QuestionBankViewModel=function(){
    var me=this;
    var quetionSelectedCallback=null;
    me.QuestionBanks=ko.observableArray([]);
    
    
    me.onQuestionSelected=function($quetionSelectedCallback){
        if($quetionSelectedCallback===undefined ||$quetionSelectedCallback===null ){
            throw new Error("$quetionSelectedCallback is not a function");
        }
        quetionSelectedCallback=$quetionSelectedCallback;
    };
    
    me.SelectQuestion=function(item){
        quetionSelectedCallback(item);
    };
    me.AddQuestion=function(question){
        me.QuestionBanks.push(question);
    };
    
    me.AddQuestions=function(questions){
        for(var i;i<questions.length;i++){
            me.AddQuestion(questions[i]);
        }
    };
};