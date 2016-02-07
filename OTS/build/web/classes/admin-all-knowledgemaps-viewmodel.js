var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.AdminAllKnowledgeMap=function(){
    var me=this;
    me.knowledgeMapList=ko.observableArray([]);
    
    $(function(){
        
        $.post("KnowledgeMapServlet",{action:"ListAllIgnoreConceptSchema"},function(msg){
            try{
               var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
               for(var i=0;i<contents.length;i++){
                  if(contents[i].IsPublic==true){
                      contents[i].Public="Yes";
                  }
                  else{
                      contents[i].Public="No" ;
                  }
                 me.knowledgeMapList.push(contents[i]);
                }   
            }catch(ex){
                
                alert(ex);
            }
        });
    });
    
};

