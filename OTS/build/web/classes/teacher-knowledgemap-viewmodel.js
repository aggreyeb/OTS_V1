var OTS=OTS||{};
OTS.ViewModels=OTS.ViewModels||{};
OTS.ViewModels.TeacherKnowledgeMapList=function(conceptHirarchyViewModel){
    var me=this;
     me.conceptHirarchyViewModel=conceptHirarchyViewModel;
     me.knowledgeMaplist=ko.observableArray([]);
                                           
      me.actionType={UNKNOWN:"",NEW:"new",RENAME:"rename",DELETE:"delete",DUPLICATE:"duplicate"};
      me.formHeadingType={CreateNew:"Create New",
      Rename:"Rename",Duplicate:"Duplicate",Delete:"Delete"};
  
      me.dialogType={CRITICAL:"Critical !",SUCCESS: "Success !", FAIL :"Fail !"};
      me.selectedItem=null;
      me.currentAction=null;
      me.showLoadingMessage=function(){
           $("#div-loadingMessage").show();
      };
     
      me.hideLoadingMessage=function(){
           $("#div-loadingMessage").hide('slow');
      };
     $.post("KnowledgeMapServlet",{action:"listuserconceptnode"},function(msg){
        try{
         me.showLoadingMessage();
    
          var message =JSON.parse(msg);
             var contents=JSON.parse(message.response.content);
              me.knowledgeMaplist([]);
              for(var i=0;i<contents.length;i++){
                  var item=JSON.parse(contents[i]);
                  if(item.description==undefined){
                      item.description="";
                  }
                  item.isRoot=true;
                  me.knowledgeMaplist.push(item);
              }
           me.hideLoadingMessage();
       }
       catch(ex){
           alert(ex);
       }
     });
  
    $(function(){
      //initialize 
      me.currentAction=me.actionType.UNKNOWN;
      me.hideform();
     
    })
  
   /* form start  */
  
    me.form={
        name:ko.observable(""),
        description:ko.observable(""),
        heading:ko.observable(me.formHeadingType.CreateNew),
        visible:ko.observable(false),
        responseMessageVisible:ko.observable(false),
        responseDialog:ko.observable(me.dialogType.CRITICAL),
        responseMessageText:ko.observable(""),
        responseBoxStyle:ko.observable("alert alert-danger"),
        closeMessageBox:function(event){
            me.form.responseMessageVisible(false);
        }
    };
    
    /* form end */
  
   me.changeFormHeading=function(name){
      me.form.heading(name);
    };
  
   me.showform=function(){
   
     me.form.visible(true);
    }; 
    
   me.hideform=function(){
      me.form.visible(false);
    }; 
    
   me.resetForm=function(){
      me.form.name("");
      me.form.description("");
    };
   
  me.hideMessageBox=function(){
        me.form.responseMessageVisible(false);
    };
    
  me.showMessageBox=function(){
       me.form.responseMessageVisible(true);
    };
    me.closeMessageBox=function(event){
     
        me.hideMessageBox();
    };
     /*  List commands*/
     
     me.onNew=function(event){
         me.changeFormHeading(me.formHeadingType.CreateNew);
         me.hideMessageBox();
         me.resetForm();
         me.selectedItem=null;
         me.currentAction=me.actionType.NEW;
         me.showform();
     };  
    
  me.onCancelNew=function(){
        me.resetForm();
        me.hideform();
        me.hideMessageBox();
        me.form.name("");
        me.form.description("");
    };
    
  me.onSubmitNew=function(){
       var action= me.currentAction;
        var id=0;
       if(me.selectedItem!=null){
           id=me.selectedItem.id;
       }
      
       var name=me.form.name();
       var description=me.form.description();
       var data={action:action,ID:id, Name:name, Description:description};
         
        $.post("KnowledgeMapServlet",data,function(msg){
            if(msg != ""){ 
                var message =JSON.parse(msg);
                if(message.response.status==="ok"){
                  var contents=JSON.parse(message.response.content);  
                  me.updatelist(me.currentAction,contents);
                  me.form.visible(false); 
                }
                else{
                    me.form.responseDialog(me.dialogType.FAIL);
                    me.form.responseMessageText(message.response.error);
                    me.form.responseBoxStyle("alert alert-info");
                    me.form.responseMessageVisible(true); 
                }
            }
            else{
                 me.form.responseDialog(me.dialogType.FAIL);
                 me.form.responseMessageText(message.response.error);
                 me.form.responseBoxStyle("alert alert-info");
                 me.form.responseMessageVisible(true);
                 me.updatelist("exception",contents);
            }
            me.form.name("");
            me.form.description("");
        });
 
    };
   
   me.updatelist=function(action, item){
       switch(action){
           case "new":
           me.knowledgeMaplist.push(item); 
           me.resetForm();
           me.form.responseDialog(me.dialogType.SUCCESS);
           me.form.responseMessageText("New knowledge map created");
           me.form.responseBoxStyle("alert alert-success");
           me.form.responseMessageVisible(true);
           break;
           case "rename":
           var  item1= me.findKnowledgeMap(item.id);
           var index= me.knowledgeMaplist.indexOf(item1);
           me.knowledgeMaplist.splice(index,1,item);
           me.form.responseDialog(me.dialogType.SUCCESS);
           me.form.responseMessageText("Knowledge map renamed");
           me.form.responseBoxStyle("alert alert-success");
           me.form.responseMessageVisible(true);
           me.resetForm();
           break;
           case "duplicate":
           me.knowledgeMaplist.push(item); 
           me.resetForm();
           me.form.responseDialog(me.dialogType.SUCCESS);
           me.form.responseMessageText("Knowledge map duplicated");
           me.form.responseBoxStyle("alert alert-success");
           me.form.responseMessageVisible(true);
           break;
          case "delete":
           var  itemToDelete= me.findKnowledgeMap(item.id);
           me.knowledgeMaplist.remove(itemToDelete);
           me.resetForm();
           me.form.responseDialog(me.dialogType.SUCCESS);
           me.form.responseMessageText("Knowledge map deleted");
           me.form.responseBoxStyle("alert alert-success");
           me.form.responseMessageVisible(true);
           me.selectedItem=null;
              break;
          case "exception":
           me.form.responseDialog(me.dialogType.FAIL);
           me.form.responseMessageText("Unable to perform operation");
           me.form.responseBoxStyle("alert alert-info");
           me.form.responseMessageVisible(true);
              break;
          default:
              break;
           
       }
   };
   
    me.findKnowledgeMap=function(id){
         var item=null;
           for(var i=0;i<me.knowledgeMaplist().length; i++){
               if(me.knowledgeMaplist()[i].id==id){
                   item= me.knowledgeMaplist()[i];
                   break;
               }
           }
           return item;
     };
   
   me.showSuccessMessage=function(message){
        me.form.responseBoxStyle("alert-success");
        me.form.responseDialog(me.dialogType.SUCCESS);
        me.form.responseMessageText(message);
        me.form.responseMessageVisible(true);
   };
   me.fillForm=function(item){
      me.form.name(item.label);
      me.form.description(item.description);
   };
   
    me.onRename=function(item,event){
        me.selectedItem=item;
        me.currentAction=me.actionType.RENAME;
        me.changeFormHeading(me.formHeadingType.Rename);
        me.hideMessageBox();
        me.fillForm(item);
        me.showform();
    };
    
   
    me.onDeplicate=function(item,event){
       
       me.form.name("");
       me.form.description("");
       
        var label=item.label ;
        label+= " " + "Copy";
        
        var description=item.description;
        description+=" " +"Copy";
       // item.label=item.label + " " + "Copy";
       // item.description=item.description + " " +"Copy";
        me.selectedItem=item;
        me.currentAction=me.actionType.DUPLICATE;
        me.changeFormHeading(me.formHeadingType.Duplicate);
        me.hideMessageBox();
       // me.fillForm(item);
       me.form.name(label);
       me.form.description(description);
        me.showform();
    };
    
    me.onDelete=function(item,event){
        me.selectedItem=item;
        me.currentAction=me.actionType.DELETE;
        me.changeFormHeading(me.formHeadingType.Delete);
        me.fillForm(item);
        me.form.responseDialog(me.dialogType.CRITICAL);
        me.form.responseMessageText("Are you sure you want to delete knowledge map ("+ item.id + ") ?");
        me.form.responseBoxStyle("alert alert-danger");
        me.form.responseMessageVisible(true);
        me.showform();
    };
   
    me.onConceptHierarchy=function(item,event){
        me.conceptHirarchyViewModel.loadView(item);
    };
  
}

