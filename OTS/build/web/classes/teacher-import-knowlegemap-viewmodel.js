var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.KnowledgeMap=function(){
    var me=this;
    me.id=ko.observable();
    me.label=ko.observable();
    me.description=ko.observable();
    me.selected=ko.observable(1);
}
OTS.ViewModels.ImportKnowlegeMapView=function(){
    var me=this;
   // me.knowledgemapList=ko.observableArray([{id:1,name:"Garden",description:"Garden Concept",selected:true},
                                        //     {id:2,name:"Vegetable",description:"Vegetable Concept",selected:true}]);  
    
     me.knowledgemapList=ko.observableArray([]);  
     me.responseMessageVisible=ko.observable(false);
     me.responseBoxStyle=ko.observable("alert alert-success");
     me.responseDialog=ko.observable("Success!");
     me.responseMessageText=ko.observable("Selected items has been imported to your knowledgemaps");
     me.CheckAll=ko.observable(false);
     me.closeMessageBox=function(event){
         me.responseMessageVisible(false);
     };
    
     
    $(function(){
         me.knowledgemapList([]);
        $.post("KnowledgeMapServlet",{action:"ImportKnowledgeMapsList"},function(msg){
            try{
               var message =JSON.parse(msg);
               var contents=JSON.parse(message.response.content);
             
               for(var i=0;i<contents.length;i++){
                 var item=JSON.parse(contents[i]);
                 var observableItem=ko.mapping.fromJS(item);
                 me.knowledgemapList.push(observableItem);
                }
                
            }catch(ex){
                
                alert(ex);
            }
        });
        
       
    });

    me.CheckAllItems=function(){
      
        var state=me.CheckAll();
        me.ToogleQuestionBankItemsSelection(state);
    };
    
     me.ToogleQuestionBankItemsSelection=function(state){
     var items= ko.toJS(me.knowledgemapList());
     for(var i=0;i<items.length;i++){
          items[i].selected=state
     }
     me.knowledgemapList([]);
      for(var i=0;i<items.length;i++){
          me.knowledgemapList.push(items[i]);
     }
  };
    
    me.onImport=function(){
       
         var selectedKnowledgeMaps=[];
        for(var i=0;i<me.knowledgemapList().length;i++){
            if(me.knowledgemapList()[i].selected()==1){
                selectedKnowledgeMaps.push(me.knowledgemapList()[i].id());
            }
        }
       var ids=  selectedKnowledgeMaps.join(",");
       if(selectedKnowledgeMaps.length==0){
           //nothing selected display message and return
            me.responseMessageVisible(true);
            me.responseMessageText("Please check knowledgemap(s) and try again")
            me.responseBoxStyle("alert alert-danger");
            me.responseDialog("Critical!");
       }
       else{
       $.post("KnowledgeMapServlet",{action:"import",knowledgemapsIds:ids},function(msg){
             try{
                 
                  var message =JSON.parse(msg);
                  var status=message.response.status;
                  if(status==="ok"){
                      me.responseMessageVisible(true);
                      me.responseBoxStyle("alert alert-success");
                      me.responseDialog=("Success!");
                  }
                  else{
                     me.responseMessageVisible(true);
                     me.responseBoxStyle("alert alert-info");
                     me.responseDialog=("Fail!");
                     me.responseMessageText(message.response.error);
                  }
             }catch(ex){
                    alert(ex);
             }
         });
     }
    };
};

