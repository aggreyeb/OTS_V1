/* global ko */

var OTS=OTS || {};
OTS.ViewModels=OTS.ViewModels || {};
OTS.QuestionBankItem=function(){
  var me=this;
    me.QuestionBankId=0;
    me.QuestionText="";
    me.GroupId="";
    me.CognitiveLevelTypeId=0;
    me.QuestionNatureTypeId=0;
    me.QuestionTypeId=0;
    me.TestId=0;
    me.CognitiveTypeName="";
    me.QuestionNatureTypeName="";
    me.QuestionTypeName="";
    me.IsTrueOrFalseQuestion=function(){
        return me.QuestionTypeId===1;
    };
    
    me.IsMultipleChoiceMultipleAnswers=function(){
        return me.QuestionTypeId===3 || me.CognitiveLevelTypeId===4;
    };
    
    me.Update=function(item){
        me.QuestionBankId(item.QuestionBankId);
        me.QuestionText(item.QuestionText);
        me.GroupId(item.GroupId);
        me.CognitiveLevelTypeId=(item.CognitiveLevelTypeId);
        me.QuestionNatureTypeId=(item.QuestionNatureTypeId);
        me.QuestionTypeId(item.QuestionTypeId)
        me.TestId(item.TestId);
        me.CognitiveTypeName(item.CognitiveTypeName);
        me.QuestionNatureTypeName(item.QuestionNatureTypeName);
        me.QuestionTypeName(item.QuestionTypeName);
    };
};


